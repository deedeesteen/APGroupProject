import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DatePicker {
    int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
    int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
    JLabel l = new JLabel("", JLabel.CENTER);
    String day = "";
    JDialog d;
    JButton[] buttons = new JButton[49];

    public DatePicker(JFrame parent) {
        d = new JDialog();
        d.setModal(true);
        String[] header = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };
        JPanel p1 = new JPanel(new GridLayout(7, 7));
        p1.setPreferredSize(new Dimension(430, 120));

        // JButton[] buttons = new JButton[49];
        for (int x = 0; x < 6; x++) {
            JButton button = new JButton(); // Create a new JButton
            buttons[x] = button; // Store it in the array

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    day = button.getActionCommand(); // Here, button refers to the current button
                    d.dispose();
                }
            });

            if (x < 7) {
                button.setText(header[x]);
                button.setForeground(Color.red);
            }
            p1.add(button);
        }

        JPanel p2 = new JPanel(new GridLayout(1, 3));
        JButton previous = new JButton("<>");
        JButton next = new JButton("<>");

        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                month++;
                displayDate();
            }

        });

        p2.add(next);
        d.add(p1, BorderLayout.CENTER);
        d.add(p2, BorderLayout.SOUTH);
        d.pack();
        d.setLocationRelativeTo(parent);
        displayDate();
        d.setVisible(true);
    }

    public void displayDate() {
        for (int x = 7; x < buttons.length; x++)
            buttons[x].setText("");
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "MMMM yyyy");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month, 1);
        int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
        int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
        for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++)
            buttons[x].setText("" + day);
        l.setText(sdf.format(cal.getTime()));
        d.setTitle("Date Picker");
    }

    public String setPickedDate() {
        if (day.equals(""))
            return day;
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "dd-MM-yyyy");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month, Integer.parseInt(day));
        return sdf.format(cal.getTime());
    }

    public void initialiseDate()
    {
         JLabel label = new JLabel("Selected Date:");
            final JTextField text = new JTextField(20);
            JButton b = new JButton("popup");
            JPanel p = new JPanel();
            p.add(label);
            p.add(text);
            p.add(b);
            final JFrame f = new JFrame();
            f.getContentPane().add(p);
            f.pack();
            f.setVisible(true);
            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    text.setText(new DatePicker(f).setPickedDate());
                }
            });
    }
    
}
    


