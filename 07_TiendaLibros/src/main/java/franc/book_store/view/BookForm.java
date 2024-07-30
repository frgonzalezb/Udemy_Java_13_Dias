package franc.book_store.view;

import franc.book_store.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

@Component
public class BookForm extends JFrame {
    private BookService bookService;
    private JPanel panel;
    private JTable bookTable;
    private DefaultTableModel bookTableModel;

    @Autowired
    public BookForm(BookService bookService) {
        this.bookService = bookService;
        initForm();
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

    private void createUIComponents() {
        // bookTable
        this.bookTableModel = new DefaultTableModel();
        this.bookTable = new JTable(bookTableModel);
        this.bookTableModel.addColumn("ID");
        this.bookTableModel.addColumn("Title");
        this.bookTableModel.addColumn("Author");
        this.bookTableModel.addColumn("Description");
        this.bookTableModel.addColumn("Price");
        this.bookTableModel.addColumn("Stock");
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
}
