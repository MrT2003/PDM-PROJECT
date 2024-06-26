package src;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NGHĨA
 */
public class newframe2 extends JFrame {
    private boolean isAdmin = false;
    private boolean isDoctor = false;
    private String PatientName;
    private  boolean HasLogin = false;

    /**
     * Creates new form newframe1
     */

    public newframe2() {
    //    Gradient_Frame GF = new Gradient_Frame();
        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        jTable1.setDefaultRenderer(Object.class , new TableGradientCell());
    }
    public static class Gradient_Panel extends JPanel{
        protected  void paintComponent(Graphics g){
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            Color color = Color.decode("#dae2f8");
            Color color1 = Color.decode("#d6a4a4");
            GradientPaint gp = new GradientPaint(0,0,color,180,height,color1);
            g2d.setPaint(gp);
            g2d.fillRect(0,0,width,height);

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jScrollPane1 = new JScrollPane();
        jList1 = new JList<>();
        jLabel2 = new JLabel();
        jTextField2 = new JTextField();
        button1 = new Button();
        jSeparator1 = new JSeparator();
        jComboBox1 = new JComboBox<>();
        button2 = new Button();
        button3 = new Button();
        jComboBox2 = new JComboBox<>();
        jScrollPane2 = new JScrollPane();
        jTable1 = new JTable();

        jSeparator1.setForeground(Color.BLACK);

        jList1.setModel(new AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Query:");
        jTable1.setModel(new DefaultTableModel(
                new Object [][] {
                        {null, null, null, null},
                },
                new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4", "Title 5 " , " Title 6" , " Title 7"
                }
        ));
        jTable1.setGridColor(Color.decode("#fe8c00"));
        jTable1.setShowGrid(true);
        Color color = new Color(255,255,240);
        jTable1.setFont(new Font("Calibre " , Font.BOLD, 13));
        jTable1.setForeground(color);

        jScrollPane2.setViewportView(jTable1);

        button1.setLabel("Run");
        button1.setBackground(Color.BLACK);
        button1.setForeground(Color.white);
        button1.setFocusable(false);
        //button Run
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                button1ActionPerformed(evt);
                try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(newframe2.class.getName()).log(Level.SEVERE, null, ex);
                }
                String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=nghia;encrypt=false;user=sa;password=Quenroi6212@";
                try(Connection con = DriverManager.getConnection(connectionUrl) ; Statement stmt = con.createStatement()) {
                    String SQL = jTextField2.getText();
                    ResultSet rs = stmt.executeQuery(SQL);
                    ResultSetMetaData rsmd = rs.getMetaData();
                    jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    int cols = rsmd.getColumnCount();
                    String[] colName = new String[cols];
                    for (int i = 0 ; i< cols ; i++){
                        colName[i] = rsmd.getColumnName(i+1);
                        System.out.println("Column " + i + ": " + colName[i]);
                    }
                    String product_id , product_name  , brand_id , category_id , model_year , list_price;
                    while(rs.next()){// phan de show data
                        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                        model.setColumnIdentifiers(colName);   // get the name of column
                        product_id = rs.getString(1);
                        product_name = rs.getString(2);
                        brand_id = rs.getString(3);
                        category_id = rs.getString(4);
                        model_year = rs.getString(5);
                        list_price = rs.getString(6);
                        String[] row = { product_id , product_name , brand_id ,category_id ,model_year ,list_price};
                        model.addRow(row);
                    }
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(newframe2.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        jComboBox1.setModel(new DefaultComboBoxModel<>(new String[] { "Doctor" , "Nurse" }));
        jComboBox1.setBackground(Color.black);

        jComboBox1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
                JComboBox CB = (JComboBox) evt.getSource();
                String selection = (String) CB.getSelectedItem();
                if (Objects.equals(selection, "Doctor")){
                    isDoctor = true;
                }
                if (Objects.equals(selection, "Nurse")){
                    isDoctor = false;
                }

                if (isDoctor == true){
                    try {
                        // TODO add your handling code here:

                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(newframe2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=nghia;encrypt=false;user=sa;password=Quenroi6212@";
                    try (Connection con = DriverManager.getConnection(connectionUrl); Statement sstmt = con.createStatement()) {
                        String Command = null;
                        if (isAdmin == true){
                            Command ="SELECT\n" +
                                    "  d.ID AS 'ID',\n" +
                                    "  e.Name AS 'Doctor_name',\n"+
                                    "  f.Fal_name AS 'Department',\n" +
                                    "  d.Doctor_Degree AS 'Degree',\n" +
                                    "  e.Contact_info AS 'Contact_infor'\n" +
                                    "FROM Doctor d\n" +
                                    "INNER JOIN Faculty f ON d.Fal_ID = f.Fal_ID\n" +
                                    "INNER JOIN Employee e ON d.ID = e.ID;";
                        }
                        if (isAdmin == false){
                            Command = "SELECT\n" +
                                    "  e.Name AS 'Doctor_Name',\n" +
                                    "  f.Fal_name AS 'Department',\n" +
                                    "  e.Contact_info AS 'Doctor_Contact_Info'\n" +
                                    "FROM Doctor d\n" +
                                    "INNER JOIN Faculty f ON d.Fal_ID = f.Fal_ID\n" +
                                    "INNER JOIN Employee e ON d.ID = e.ID";
                        }
                        ResultSet rs = sstmt.executeQuery(Command);
                        ResultSetMetaData rsmd = rs.getMetaData();
                        jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                        int cols = rsmd.getColumnCount();
                        System.out.println("The number of colum: " + cols);
                        String[] colName = new String[cols];
                        for (int i = 0 ; i< cols ; i++){
                            colName[i] = rsmd.getColumnName(i+1);
                       //     jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRender );
                            System.out.println("Column " + i + ": " + colName[i]);
                        }
                        String a , b  , c , d , e, f,  g ,h , i ,j ;
                        //  model.setColumnIdentifiers(colName);
                        while(rs.next()){// phan de show data
                            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                            model.setColumnIdentifiers(colName);
                            a = rs.getString(1);
                            b = rs.getString(2);
                            c = rs.getString(3);
                            d = rs.getString(4);
                            e = rs.getString(5);
                            f = rs.getString(6);
                            g = rs.getString(7);
                            h = rs.getString(8);
                            i = rs.getString(9);
                            i = rs.getString(10);


                            String[] row = { a , b , c ,d ,g ,f};
                            model.addRow(row);
                        }             } catch (SQLException ex) {
                        Logger.getLogger(newframe2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if (isDoctor == false){
                    try {
                        // TODO add your handling code here:

                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(newframe2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=nghia;encrypt=false;user=sa;password=Quenroi6212@";
                    try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {
                        String Command = null;
                        if (isAdmin == true){
                            Command ="SELECT\n" +
                                    "  N.ID AS 'ID',\n" +
                                    "  e.Name AS 'Nurse_name',\n" +
                                    "  N.Caring_Type AS 'Caring Type',\n" +
                                    "e.Contact_info AS 'Contact_infor'\n"+
                                    "FROM Nurse N\n" +
                                    "INNER JOIN Employee e ON N.ID = e.ID;";
                        }
                        if (isAdmin == false){
                            Command = "SELECT\n" +
                                    "  e.Name,\n" +
                                    "  e.Contact_info AS 'Contact_infor'\n" +
                                    "FROM Nurse N\n" +
                                    "INNER JOIN Employee e ON N.ID = e.ID;";
                        }
                        ResultSet rs = stmt.executeQuery(Command);
                        ResultSetMetaData rsmd = rs.getMetaData();
                        jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                        int cols = rsmd.getColumnCount();
                        System.out.println("The number of colum: " + cols);
                        String[] colName = new String[cols];
                        for (int i = 0 ; i< cols ; i++){
                            colName[i] = rsmd.getColumnName(i+1);
                            //     jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRender );
                            System.out.println("Column " + i + ": " + colName[i]);
                        }
                        String a , b  , c , d , e, f,  g ,h , i ,j ;
                        //  model.setColumnIdentifiers(colName);
                        while(rs.next()){// phan de show data
                            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                            model.setColumnIdentifiers(colName);
                            a = rs.getString(1);
                            b = rs.getString(2);
                            c = rs.getString(3);
                            d = rs.getString(4);
                            e = rs.getString(5);
                            f = rs.getString(6);
                            g = rs.getString(7);
                            h = rs.getString(8);
                            i = rs.getString(9);
                            i = rs.getString(10);


                            String[] row = { a , b , c ,d ,g ,f};
                            model.addRow(row);
                        }             } catch (SQLException ex) {
                        Logger.getLogger(newframe2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        });

        button2.setLabel("Department");
        button2.setBackground(Color.BLACK);
        button2.setForeground(Color.white);
        button2.setFocusable(false);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                button2ActionPerformed(evt);
                try {
                    // TODO add your handling code here:

                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(newframe2.class.getName()).log(Level.SEVERE, null, ex);
                }
                String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=nghia;encrypt=false;user=sa;password=Quenroi6212@";
                try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {
                    String Command = null;
                    if (isAdmin == true){
                        Command ="SELECT \n" +
                                "e.Fal_ID AS 'Falculty_ID',\n"+
                                "  e.Name AS 'Doctor_Name', d.Doctor_Degree, f.Fal_name AS 'Department'\n" +
                                "FROM Doctor d\n" +
                                "INNER JOIN Employee e ON d.ID = e.ID\n" +
                                "INNER JOIN Faculty f ON e.Fal_ID = f.Fal_ID\n" +
                                "ORDER BY  f.Fal_name , e.Fal_ID  ;";
                    }
                    if (isAdmin == false){
                        Command ="SELECT \n" +
                                "  e.Name AS 'Doctor_Name', f.Fal_name AS 'Department'\n" +
                                "FROM Doctor d\n" +
                                "INNER JOIN Employee e ON d.ID = e.ID\n" +
                                "INNER JOIN Faculty f ON e.Fal_ID = f.Fal_ID\n" +
                                "ORDER BY  f.Fal_name , e.Fal_ID  ;";
                    }
                    ResultSet rs = stmt.executeQuery(Command);
                    ResultSetMetaData rsmd = rs.getMetaData();
                    jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    int cols = rsmd.getColumnCount();
                    System.out.println("The number of colum: " + cols);
                    String[] colName = new String[cols];
                    for (int i = 0 ; i< cols ; i++){
                        colName[i] = rsmd.getColumnName(i+1);
                        //     jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRender );
                        System.out.println("Column " + i + ": " + colName[i]);
                    }
                    String a , b  , c , d , e, f,  g ,h , i ,j ;
                    //  model.setColumnIdentifiers(colName);
                    while(rs.next()){// phan de show data
                        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                        model.setColumnIdentifiers(colName);
                        a = rs.getString(1);
                        b = rs.getString(2);
                        c = rs.getString(3);
                        d = rs.getString(4);
                        e = rs.getString(5);
                        f = rs.getString(6);
                        g = rs.getString(7);
                        h = rs.getString(8);
                        i = rs.getString(9);
                        i = rs.getString(10);


                        String[] row = { a , b , c ,d ,g ,f};
                        model.addRow(row);
                    }             } catch (SQLException ex) {
                    Logger.getLogger(newframe2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        button3.setLabel("History");
        button3.setBackground(Color.BLACK);
        button3.setForeground(Color.white);
        button3.setFocusable(false);
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                button3ActionPerformed(evt);
                try {
                    // TODO add your handling code here:

                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(newframe2.class.getName()).log(Level.SEVERE, null, ex);
                }
                String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=nghia;encrypt=false;user=sa;password=Quenroi6212@";
                try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement()) {
                    String Command = null;
                    if (isAdmin == true){
                        Command= "\n" +
                                "WITH Doctor_Name AS(\n" +
                                "SELECT Employee.Name AS 'Name',Employee.ID AS 'ID'\n" +
                                "FROM Employee\n" +
                                "INNER JOIN Doctor ON Employee.ID = Doctor.ID)\n" +
                                "SELECT\n" +
                                "    Doctor_Name.ID AS 'DID',\n" +
                                "\tDoctor_Name.Name AS 'DName',\n" +
                                "    Patient.PID,\n" +
                                "    Patient.Pname,\n" +
                                "    Patient.Gender AS 'PGender',\n" +
                                "    Bill.Bill_ID,\n" +
                                "    Bill.Room_Cost,\n" +
                                "    Bill.Medi_Cost,\n" +
                                "    Bill.Other_Charges,\n" +
                                "    (Bill.Room_Cost + Bill.Medi_Cost + Bill.Other_Charges) AS Total_Cost,\n" +
                                "\tBill.Bill_Date\n" +
                                "FROM  Patient\n" +
                                "JOIN Examined ON Patient.PID = Examined.PID\n" +
                                "JOIN Bill ON Bill.PID = Examined.PID\n" +
                                "JOIN Doctor ON Doctor.ID = Examined.ID\n" +
                                "JOIN Doctor_Name ON Doctor_Name.ID = Examined.ID \n" +
                                "ORDER BY Bill_Date\n";

                    }
                    if (isAdmin == false && HasLogin == true){
                        Command ="WITH Reg_Infor AS (\n" +
                                "    SELECT \n" +
                                "   Registration.PID AS 'PID'\n" +
                                "    FROM \n" +
                                "        Registration\n" +
                                ")\n" +
                                "SELECT\n" +
                                "    Employee.Name AS 'DName',\n" +
                                "    Patient.PID,\n" +
                                "    Patient.Pname,\n" +
                                "    Patient.Gender AS 'PGender',\n" +
                                "    Bill.Bill_ID,\n" +
                                "    Bill.Room_Cost,\n" +
                                "    Bill.Medi_Cost,\n" +
                                "    Bill.Other_Charges,\n" +
                                "    (Bill.Room_Cost + Bill.Medi_Cost + Bill.Other_Charges) AS Total_Cost,\n" +
                                "    Bill.Bill_Date\n" +
                                "FROM \n" +
                                "    Patient\n" +
                                "LEFT JOIN \n" +
                                "    Examined ON Patient.PID = Examined.PID\n" +
                                "LEFT JOIN \n" +
                                "    Bill ON Bill.PID = Patient.PID\n" +
                                "LEFT JOIN \n" +
                                "    Doctor ON Doctor.ID = Examined.ID\n" +
                                "LEFT JOIN \n" +
                                "    Reg_Infor ON Reg_Infor.PID = Patient.PID\n" +
                                "LEFT JOIN \n" +
                                "    Employee ON Doctor.ID = Employee.ID\n" +
                                "WHERE \n" +
                                "    Patient.Pname = '"+PatientName +"'";
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Please login to see the history");
                    }

                    // product_name =  +
                    ResultSet rs = stmt.executeQuery(Command);
                    ResultSetMetaData rsmd = rs.getMetaData();
                    jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    int cols = rsmd.getColumnCount();
                    System.out.println("The number of colum: " + cols);
                    String[] colName = new String[cols];
                    for (int i = 0 ; i< cols ; i++){
                        colName[i] = rsmd.getColumnName(i+1);
                        System.out.println("Column " + i + ": " + colName[i]);
                    }
                    String a , b  , c , d , e , f , g, h , i , j , k , l , m ,n ,o ;
                    //  model.setColumnIdentifiers(colName);
                    while(rs.next()){// phan de show data
                        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                        model.setColumnIdentifiers(colName);
                        a = rs.getString(1);
                        b = rs.getString(2);
                        c = rs.getString(3);
                        d = rs.getString(4);
                        e = rs.getString(5);
                        f = rs.getString(6);
                        g = rs.getString(7);
                        h = rs.getString(8);
                        i = rs.getString(9);
                        j = rs.getString(10);
                        k = rs.getString(11);
                        l = rs.getString(12);
                        m = rs.getString(13);
                        n = rs.getString(14);
                        o = rs.getString(15);

                        String[] row = { a , b , c ,d ,g ,f};
                        model.addRow(row);
                    }             } catch (SQLException ex) {
                    Logger.getLogger(newframe2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        jComboBox2.setModel(new DefaultComboBoxModel<>(new String[] { "Admin" , "Patient" }));
        jComboBox2.setBackground(Color.black);
        jComboBox2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
                JComboBox CB = (JComboBox) evt.getSource();
                String selection = (String) CB.getSelectedItem();
                if (Objects.equals(selection, "Admin")){
                    isAdmin = true;
                    JOptionPane.showMessageDialog(new JFrame(),  "Welcome back Admin");
                }
                if (Objects.equals(selection, "Patient")){
                    isAdmin = false;
                    JFrame frame = new JFrame();
                    frame.setSize(500, 300);
                     Gradient_Panel panel = new Gradient_Panel();
                    JTextField jTextField3 = new JTextField();
                    jTextField3.setColumns(31);
                    JLabel jLabel2 = new JLabel();
                    jLabel2.setForeground(Color.WHITE);

                    JLabel jLabel5 = new JLabel();
                    jLabel5.setForeground(Color.WHITE);

                    JButton button4 = new JButton("Login");
                    button4.setBackground(Color.black);
                  //  button4.setFocusPainted(false);

                    // Set properties
                    jLabel2.setText("Patient name:");
                    jLabel2.setForeground(Color.BLACK);
                    jLabel2.setFont(new Font("Calibre", Font.BOLD, 12));
                    jLabel5.setText("LOGIN");
                    jLabel5.setForeground(Color.BLACK);
                    jLabel5.setFont(new Font("Calibre", Font.BOLD, 16));

                    // Add action listener to button
                    button4.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            button4ActionPerformed(evt);
                            String name = jTextField3.getText();
                            try {
                                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(newframe2.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=nghia;encrypt=false;user=sa;password = Quenroi6212@";
                            try (Connection con = DriverManager.getConnection(connectionUrl) ; PreparedStatement pstmt = con.prepareStatement("SELECT Pname FROM Patient WHERE Pname = ?")) {
                                pstmt.setString(1, name); // Bind user input to parameter 1
                                ResultSet rs = pstmt.executeQuery();
                                ResultSetMetaData rsmd = rs.getMetaData();
                                int cols = rsmd.getColumnCount();
                                System.out.println("The number of columns: " + cols);
                                String[] colName = new String[cols];
                                for (int i = 0; i< cols ; i++){
                                    colName[i] = rsmd.getColumnName(i+1);
                                    System.out.println("Column " + i + ": " + colName[i]);

                                }
                                if(rs.next()){
                                    PatientName = name;
                                    HasLogin = true;
                                    JOptionPane.showMessageDialog(null,name + " login successful !");
                                    frame.dispose();
                                }
                                else{
                                    HasLogin = false;
                                    JOptionPane.showMessageDialog(frame,  "Invalid name");
                                    frame.dispose();
                                }

                            }catch (SQLException ex) {
                                Logger.getLogger(newframe2.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });

                    // Add components to panel with custom layout
                    GroupLayout layout = new GroupLayout(panel);
                    panel.setLayout(layout);
                    layout.setHorizontalGroup(
                            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                            .addGap(frame.getX() * 2 + 240)
                                            .addComponent(jLabel5))
                                    .addGroup(layout.createSequentialGroup()

                                            .addGap(25)
                                            .addComponent(jLabel2)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addGap(5,7,15)
                                            .addComponent(jTextField3, GroupLayout.PREFERRED_SIZE,
                                                    GroupLayout.PREFERRED_SIZE,
                                                    GroupLayout.PREFERRED_SIZE ))
                                    .addGap(3,7,10)
                                    .addGroup(layout.createSequentialGroup()
                                            .addGap(frame.getX() * 2 + 235)
                                            .addComponent(button4))
                    );

                    layout.setVerticalGroup(
                            layout.createSequentialGroup()
                                    .addGap(20)
                                    .addComponent(jLabel5)
                                    .addGap(20)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel2)
                                            .addComponent(jTextField3))
                                    .addGap(30)
                                    .addComponent(button4)
                    );

                    frame.add(panel);
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);

                }
                System.out.println("The selected item is: " + selection);

            }
        });

        JP = new Gradient_Panel();
        add(JP);
        GroupLayout layout = new GroupLayout(JP);
        JP.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(jComboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, 389, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32))
                        .addComponent(jSeparator1)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(54, 54, 54)
                                                .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
                                                .addGap(127, 127, 127)
                                                .addComponent(button2, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
                                                .addGap(136, 136, 136)
                                                .addComponent(button3, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(31, 31, 31)
                                                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 1051, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(button2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(14, 14, 14)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(button1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(jLabel2)
                                                                .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jComboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                                .addGap(53, 53, 53)
                                                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(14, 14, 14)
                                                                .addComponent(button3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE)))))
                                .addGap(46, 46, 46)
                                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 626, GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38))
        );
        pack();
    }// </editor-fold>




    private void button1ActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jComboBox1ActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void button2ActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void button3ActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void button4ActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jComboBox2ActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }



    /**
     * @param args the command line arguments
     */

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        //</editor-fold>
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        /* Create and display the form */
        EventQueue.invokeLater(() -> {
            newframe2 n2 = new newframe2();
            n2.setVisible(true);
        });
    }

    // Variables declaration - do not modify
    private Button button1;
    private Button button2;
    private Button button3;
    private JComboBox<String> jComboBox1;
    private JComboBox<String> jComboBox2;
    private JLabel jLabel2;
    private JList<String> jList1;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JSeparator jSeparator1;
    private JTable jTable1;
    private JTextField jTextField2;
    private JPanel JP ;
    // End of variables declaration
}
