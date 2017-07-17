import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Factory.InputFactory;
import Model.Model;
import Model.Singleton;

public class InputForm {

	private JFrame frame;
	private Singleton singleton;
	private Model model;
	private FileDialog fd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InputForm window = new InputForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InputForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Insert Information");

		Label lbName = new Label("Name");
		lbName.setBounds(27, 22, 41, 22);
		frame.getContentPane().add(lbName);

		Label lbID = new Label("ID");
		lbID.setBounds(27, 52, 41, 22);
		frame.getContentPane().add(lbID);

		Label lbPhoneNumber = new Label("Phone Number");
		lbPhoneNumber.setBounds(27, 80, 41, 22);
		frame.getContentPane().add(lbPhoneNumber);

		Label lbAddress = new Label("Address");
		lbAddress.setBounds(27, 115, 41, 22);
		frame.getContentPane().add(lbAddress);

		Button btnAdd = new Button("Add");
		btnAdd.setBounds(60, 155, 70, 22);
		frame.getContentPane().add(btnAdd);

		Button btnOpen = new Button("Open");
		btnOpen.setBounds(175, 155, 70, 22);
		frame.getContentPane().add(btnOpen);

		Button btnSave = new Button("Save");
		btnSave.setBounds(290, 155, 70, 22);
		frame.getContentPane().add(btnSave);

		Label lbType = new Label("Type");
		lbType.setBounds(262, 22, 29, 22);
		frame.getContentPane().add(lbType);

		final TextArea txtArea = new TextArea();
		txtArea.setBounds(27, 200, 380, 184);
		
		frame.getContentPane().add(txtArea);

		final TextField txtName = new TextField();
		txtName.setBounds(74, 22, 111, 22);
		frame.getContentPane().add(txtName);

		final TextField txtID = new TextField();
		txtID.setBounds(74, 52, 111, 22);
		frame.getContentPane().add(txtID);

		final TextField txtPhoneNumber = new TextField();
		txtPhoneNumber.setBounds(74, 80, 111, 22);
		frame.getContentPane().add(txtPhoneNumber);

		final TextField txtAddress = new TextField();
		txtAddress.setBounds(74, 115, 111, 22);
		frame.getContentPane().add(txtAddress);

		CheckboxGroup ckGroup = new CheckboxGroup();
		final Checkbox cbVietNam = new Checkbox("Viet Nam", ckGroup, true);
		final Checkbox cbUS = new Checkbox("US", ckGroup, false);

		cbVietNam.setBounds(300, 22, 111, 22);
		cbUS.setBounds(300, 45, 111, 22);
		frame.getContentPane().add(cbVietNam);
		frame.getContentPane().add(cbUS);

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = txtName.getText();
				String ID = txtID.getText();
				String phoneNumber = txtPhoneNumber.getText();
				String address = txtAddress.getText();
				if (!name.isEmpty() && !ID.isEmpty() && !phoneNumber.isEmpty()
						&& !address.isEmpty()) {
					singleton = Singleton.getSingleton();
					model = new Model(name, ID, phoneNumber, address);
					if (singleton.addModel(model)) {
						try {
							if (cbUS.getState()) {
								model = InputFactory.getFactoryObject(
										"US", model).getModel();
							} else if (cbVietNam.getState()) {
								model = InputFactory.getFactoryObject(
										"Viet Nam", model).getModel();
							}
							String newText;
							if(!txtArea.getText().equals("")){
							newText = txtArea.getText()
									+ "----- \nName: " + model.getName()
									+ "\n" + model.getID() + "\nPhone: "
									+ model.getPhoneNumber() + "\n"
									+ model.getAddress() + "\n";
							}else{
								newText = "Name: " + model.getName()
								+ "\n" + model.getID() + "\nPhone: "
								+ model.getPhoneNumber() + "\n"
								+ model.getAddress() + "\n";
							}
							txtArea.setText(newText);
							txtID.setText("");
							txtName.setText("");
							txtPhoneNumber.setText("");
							txtAddress.setText("");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"ID has already existed!");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Please enter data to fields above!");
				}
			}
		});

		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtArea.setText("");
				fd = new FileDialog(frame, "Open file", FileDialog.LOAD);
				fd.setFile("*.txt");
				fd.setVisible(true);
				if (fd.getDirectory() != null && fd.getFile() != null) {
					String filePath = fd.getDirectory() + fd.getFile();
					if (!filePath.isEmpty()) {
						String content = new String();
						File file = new File(filePath);
						content += "File name opened: " + file.getName()
								+ ".\n----------\n\n";
						BufferedReader br;
						try {
							br = new BufferedReader(new FileReader(file));
							String line;
							while ((line = br.readLine()) != null) {
								content += line + "\n";
							}
							txtArea.setText(content);
							br.close();
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						System.out.println("Open command cancelled by user.");
					}
				}
			}
		});

		btnSave.addActionListener(new ActionListener() {
			private FileOutputStream fileOuput;
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (!txtArea.getText().equals("")) {
						fd = new FileDialog(frame, "Save file", FileDialog.SAVE);
						fd.setFile("*.txt");
						fd.setVisible(true);
						if (fd.getFile() != null) {
							StringBuffer sbPath = new StringBuffer(fd
									.getDirectory());
							sbPath.append("\\");
							sbPath.append(fd.getFile());
							fileOuput = new FileOutputStream(
									sbPath.toString());
							String content = txtArea.getText();
							byte[] data = new byte[content.length()];
							for (int idx = 0; idx < content.length(); idx++) {
								data[idx] = (byte) content.charAt(idx);
							}
							fileOuput.write(data);
							JOptionPane.showMessageDialog(null,
									"Save successfully!");
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "You cannot save empty text file!");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
