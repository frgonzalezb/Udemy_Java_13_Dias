package franc.book_store.view;

import franc.book_store.model.Book;
import franc.book_store.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

@Component
public class BookForm extends JFrame {
    private BookService bookService;
    private JPanel panel;
    private JTable bookTable;
    private JTextField idTextField; // dummy field
    private JTextField bookTextField;
    private JTextField authorTextField;
    private JTextField descriptionTextField;
    private JTextField priceTextField;
    private JTextField stockTextField;
    private JTextField isDeletedTextField; // dummy field
    private JButton addBookButton;
    private JButton editBookButton;
    private JButton deleteBookButton;
    private DefaultTableModel bookTableModel;

    @Autowired
    public BookForm(BookService bookService) {
        this.bookService = bookService;
        initForm();
        addBookButton.addActionListener(e -> addBook());
        editBookButton.addActionListener(e -> editBook());
        deleteBookButton.addActionListener(e -> deleteBook());
        bookTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                loadSelectedBook();
            }
        });
    }

    private void initForm() {
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(960, 720);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);
    }

    private void listBooks() {
        bookTableModel.setRowCount(0);
        this.bookService.getAllBooks().forEach(book -> {
            if (book.isDeleted()) return;
            Object[] row = new Object[6];
            row[0] = book.getId();
            row[1] = book.getTitle();
            row[2] = book.getAuthor();
            row[3] = book.getDescription();
            row[4] = book.getPrice();
            row[5] = book.getStock();
            bookTableModel.addRow(row);
        });
    }

    private void addBook() {
        if (!requireFields()) return;
        if (!validatePrice()) return;
        if (!validateStock()) return;
        String title = bookTextField.getText();
        String author = authorTextField.getText();
        String description = descriptionTextField.getText();
        double price = Double.parseDouble(priceTextField.getText());
        int stock = Integer.parseInt(stockTextField.getText());
        boolean isDeleted = false;

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setDescription(description);
        book.setPrice(price);
        book.setStock(stock);
        book.setDeleted(isDeleted);

        bookService.saveBook(book);
        showMessage("Libro guardado.");
        clearFields();
        listBooks();
    }

    private void loadSelectedBook() {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow == -1) return;
        int id = Integer.parseInt(bookTable.getValueAt(selectedRow, 0).toString());
        Book book = bookService.getBookById(id);
        if (book == null) return;
        idTextField.setText(String.valueOf(book.getId()));
        bookTextField.setText(book.getTitle());
        authorTextField.setText(book.getAuthor());
        descriptionTextField.setText(book.getDescription());
        priceTextField.setText(String.valueOf(book.getPrice()));
        stockTextField.setText(String.valueOf(book.getStock()));
        isDeletedTextField.setText(String.valueOf(book.isDeleted()));
    }

    private void editBook() {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow == -1) {
            showMessage("Debe seleccionar un libro.");
            return;
        }
        if (!requireFields()) return;
        if (!validatePrice()) return;
        if (!validateStock()) return;
        int id = Integer.parseInt(idTextField.getText());
        Book book = new Book();
        book.setId(id);
        book.setTitle(bookTextField.getText());
        book.setAuthor(authorTextField.getText());
        book.setDescription(descriptionTextField.getText());
        book.setPrice(Double.parseDouble(priceTextField.getText()));
        book.setStock(Integer.parseInt(stockTextField.getText()));
        book.setDeleted(false);
        bookService.saveBook(book);
        showMessage("Libro editado.");
        clearFields();
        listBooks();
    }

    private void deleteBook() {
        /*
        NOTA: El código original habilitaba la eliminación dura de las instancias
        en la BD, así que he comentado el código original y he decidido implementar
        aquí una eliminación suave (la instancia sigue existiendo en la base de datos).
        */
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow == -1) {
            showMessage("Debe seleccionar un libro.");
            return;
        }
        if (!confirmDeleteBook()) return;
        int id = Integer.parseInt(idTextField.getText());
        Book book = new Book();
        book.setId(id);
        book.setTitle(bookTextField.getText());
        book.setAuthor(authorTextField.getText());
        book.setDescription(descriptionTextField.getText());
        book.setPrice(Double.parseDouble(priceTextField.getText()));
        book.setStock(Integer.parseInt(stockTextField.getText()));
        book.setDeleted(true);
        bookService.saveBook(book);
        showMessage("Libro eliminado.\nSi desea recuperar la entrada, contacte al administrador.");
        clearFields();
        listBooks();
//        int selectedRow = bookTable.getSelectedRow();
//        if (selectedRow == -1) {
//            showMessage("Debe seleccionar un libro.");
//            return;
//        }
//        if (!confirmDeleteBook()) return;
//        int id = Integer.parseInt(bookTable.getValueAt(selectedRow, 0).toString());
//        bookService.deleteBook(id);
//        showMessage("Libro eliminado.");
//        clearFields();
//        listBooks();
    }

    private boolean confirmDeleteBook() {
        int option = JOptionPane.showConfirmDialog(
                this,
                "¿Desea eliminar el libro? Esta acción no se puede deshacer.",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );
        return option == JOptionPane.YES_OPTION;
    }

    private boolean requireFields() {
        List<JTextField> fields = List.of(
                bookTextField,
                authorTextField,
                descriptionTextField,
                priceTextField,
                stockTextField
        );
        List<String> fieldNames = List.of(
                "título del libro",
                "autor del libro",
                "descripción del libro",
                "precio del libro",
                "stock del libro"
        );

        for (int i = 0; i < fields.size(); i++) {
            JTextField field = fields.get(i);
            if (field.getText().isEmpty()) {
                showMessage(String.format("Se requiere el %s.", fieldNames.get(i)));
                field.requestFocusInWindow();
                return false;
            }
        }
        return true;
    }

    private boolean validatePrice() {
        String price = priceTextField.getText();
        if (!price.matches("\\d+(\\.\\d+)?")) {
            showMessage("El precio debe ser un número no negativo.");
            priceTextField.requestFocusInWindow();
            priceTextField.setText("0.0");
            return false;
        }
        if (Double.parseDouble(price) > 100000000) {
            showMessage("El precio de un libro no puede ser mayor que 100000000.");
            priceTextField.requestFocusInWindow();
            priceTextField.setText("0.0");
            return false;
        }
        return true;
    }

    private boolean validateStock() {
        String stock = stockTextField.getText();
        if (!stock.matches("\\d+")) {
            showMessage("El stock debe ser un número entero no negativo.");
            stockTextField.requestFocusInWindow();
            stockTextField.setText("0");
            return false;
        }
        if (Integer.parseInt(stock) > 1000000) {
            showMessage("El stock de un libro no puede ser mayor que 1000000.");
            stockTextField.requestFocusInWindow();
            stockTextField.setText("0");
            return false;
        }
        return true;
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private void clearFields() {
        idTextField.setText("");
        bookTextField.setText("");
        authorTextField.setText("");
        descriptionTextField.setText("");
        priceTextField.setText("");
        stockTextField.setText("");
        isDeletedTextField.setText("");
    }

    private void createUIComponents() {
        // hidden id field for retrieving book data
        idTextField = new JTextField();
        idTextField.setVisible(false);
        // hidden isDeleted field for retrieving book data
        isDeletedTextField = new JTextField();
        isDeletedTextField.setVisible(false);
        // bookTable
        this.bookTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.bookTable = new JTable(bookTableModel);
        bookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.bookTableModel.addColumn("ID");
        this.bookTableModel.addColumn("Título");
        this.bookTableModel.addColumn("Autor");
        this.bookTableModel.addColumn("Descripción");
        this.bookTableModel.addColumn("Precio (CLP)");
        this.bookTableModel.addColumn("Stock");
        listBooks();
    }
}
