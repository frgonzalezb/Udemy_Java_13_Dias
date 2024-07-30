package franc.book_store.view;

import franc.book_store.model.Book;
import franc.book_store.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.util.List;

@Component
public class BookForm extends JFrame {
    private BookService bookService;
    private JPanel panel;
    private JTable bookTable;
    private JTextField bookTextField;
    private JTextField authorTextField;
    private JTextField descriptionTextField;
    private JTextField priceTextField;
    private JTextField stockTextField;
    private JButton addBookButton;
    private JButton editBookButton;
    private JButton deleteBookButton;
    private DefaultTableModel bookTableModel;

    @Autowired
    public BookForm(BookService bookService) {
        this.bookService = bookService;
        initForm();
        addBookButton.addActionListener(e -> addBook());
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

    private void addBook() {
        if (!validateFields()) return;
        String title = bookTextField.getText();
        String author = authorTextField.getText();
        String description = descriptionTextField.getText();
        double price = Double.parseDouble(priceTextField.getText());
        int stock = Integer.parseInt(stockTextField.getText());

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setDescription(description);
        book.setPrice(price);
        book.setStock(stock);

        bookService.saveBook(book);
        showMessage("Libro guardado.");
        clearFields();
        listBooks();

    }

    private boolean validateFields() {
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

    private void listBooks() {
        bookTableModel.setRowCount(0);
        this.bookService.getAllBooks().forEach(book -> {
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

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private void clearFields() {
        bookTextField.setText("");
        authorTextField.setText("");
        descriptionTextField.setText("");
        priceTextField.setText("");
        stockTextField.setText("");
    }

    private void createUIComponents() {
        // bookTable
        this.bookTableModel = new DefaultTableModel();
        this.bookTable = new JTable(bookTableModel);
        this.bookTableModel.addColumn("ID");
        this.bookTableModel.addColumn("Título");
        this.bookTableModel.addColumn("Autor");
        this.bookTableModel.addColumn("Descripción");
        this.bookTableModel.addColumn("Precio");
        this.bookTableModel.addColumn("Stock");
        listBooks();
    }
}
