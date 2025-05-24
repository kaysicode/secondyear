package project.Server.ServerGUI;


import javax.swing.*;
import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;

public class ConsolePanel extends JTextArea {
    private PrintStream printStream;

    public ConsolePanel() {
        super();
        this.setEditable(false);
        this.setBackground(Color.BLACK);
        this.setFont(new Font("Consolas", Font.PLAIN, 14));
        this.setForeground(Color.WHITE);
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
        this.setFocusable(false);

        // Redirecting output stream
        printStream = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                SwingUtilities.invokeLater(() -> {
                    append(String.valueOf((char) b));
                    setCaretPosition(getDocument().getLength());
                });
            }

            @Override
            public void write(byte[] b, int off, int len) {
                SwingUtilities.invokeLater(() -> {
                    append(new String(b, off, len));
                    setCaretPosition(getDocument().getLength());
                });
            }
        });
    }

    public PrintStream getPrintStream() {
        return printStream;
    }
}
