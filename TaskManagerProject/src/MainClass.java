import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainClass {

	private JFrame frame;
	private JTextField name;
	private JTextField c;
	private JTextField afati;
	private JTextField statusi;
	private JTable table;
	
	int row;
	ArrayList<Detyrat> lista;
	DefaultTableModel dtm;
	String header[]= new String[]{"Detyra","Komenti","Afati","Statusi"};
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainClass window = new MainClass();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void displayDetails(){
		dtm.setRowCount(0);
		for(int i=0; i<lista.size();i++){
			Object[] obj={lista.get(i).name,lista.get(i).c,lista.get(i).afati,lista.get(i).statusi};
			dtm.addRow(obj);
		}
	}

//Main//
	public MainClass() {
		initialize();
		lista=new ArrayList<>();
		dtm=new DefaultTableModel(header,0);
		table.setModel(dtm);
	}

//JFrame//
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Task Manager");
		frame.getContentPane().setBackground(new Color(200, 200, 200));
		frame.setBounds(100, 100, 1080, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel labelDetyra = new JLabel("Detyra");
		labelDetyra.setBounds(30, 30, 100, 30);
		frame.getContentPane().add(labelDetyra);
		
		name = new JTextField();
		name.setBounds(130, 40, 110, 20);
		frame.getContentPane().add(name);
		name.setColumns(10);
		
		JLabel labelC = new JLabel("Komenti");
		labelC.setBounds(30, 70, 100, 30);
		frame.getContentPane().add(labelC);
		
		c = new JTextField();
		c.setColumns(10);
		c.setBounds(130, 70, 110, 20);
		frame.getContentPane().add(c);
		
		JLabel labelAfati = new JLabel("Afati");
		labelAfati.setBounds(30, 105, 100, 25);
		frame.getContentPane().add(labelAfati);
		
		afati = new JTextField();
		afati.setColumns(10);
		afati.setBounds(130, 110, 111, 20);
		frame.getContentPane().add(afati);
		
		JLabel labelStatusi = new JLabel("Statusi");
		labelStatusi.setBounds(30, 145, 100, 20);
		frame.getContentPane().add(labelStatusi);
		
		statusi = new JTextField();
		statusi.setColumns(10);
		statusi.setBounds(130, 150, 110, 20);
		frame.getContentPane().add(statusi);
		
		JButton buttonAdd = new JButton("Shto");
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Detyrat data=new Detyrat(name.getText(),c.getText(),afati.getText(),statusi.getText());
				lista.add(data);
				displayDetails();
			}
		});
		buttonAdd.setBounds(30, 190, 90, 30);
		frame.getContentPane().add(buttonAdd);
		
		JButton buttonDelete = new JButton("Fshi");
		buttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice=JOptionPane.showConfirmDialog(null, "A doni të fshini detyrën ?", "Fshi",JOptionPane.YES_NO_OPTION);
				if(choice==0){
					dtm.removeRow(row);
					lista.remove(row);
					displayDetails();
				}
				
			}
		});
		buttonDelete.setBounds(140, 190, 90, 30);
		frame.getContentPane().add(buttonDelete);
		
		JButton buttonEdit = new JButton("Modifiko");
		buttonEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lista.get(row).name=name.getText();
				lista.get(row).c=c.getText();
				lista.get(row).afati=afati.getText();
				lista.get(row).statusi=statusi.getText();
				displayDetails();
			}
		});
		buttonEdit.setBounds(80, 230, 90, 30);
		frame.getContentPane().add(buttonEdit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(280, 40, 740, 340);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				row=table.getSelectedRow();
				
				name.setText(dtm.getValueAt(row, 0).toString());
				c.setText(dtm.getValueAt(row, 1).toString());
				afati.setText(dtm.getValueAt(row, 2).toString());
				statusi.setText(dtm.getValueAt(row, 3).toString());
				
			}
		});
		scrollPane.setViewportView(table);
	}

}
