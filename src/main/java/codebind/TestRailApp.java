package codebind;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class TestRailApp extends Base implements ActionListener {

	private static final Dashboard dashboard = new Dashboard();
	public static String userName = null;
	public static String password = null;
	public static String SUIT_NAME = null;
	public static String SECTION_NAME = null;
	public static String PROJECT_NAME = null;
	public static String path = null;
	public static List<String> paths = new ArrayList<String>();
	public static List<String> suitsName = new ArrayList<String>();
	public static List<String> sectionsName = new ArrayList<String>();
	public static JComboBox project = null;
	public static JComboBox suit = null;
	public static JComboBox section = null;
	public static List<String> names;

	// main 2
	public static JPanel jPanel2 = null;
	public static JFrame frame = null;
	public static JProgressBar progressBar = null;
	public static JButton browse = null;
	public static JButton uploadButton = null;
	private static JFormattedTextField textField;
	private static JFrame jFrame = null;
	private static JLabel userNameLabel;
	private static JLabel passwordLabel;
	private static JTextField userNameText;
	private static JPasswordField passwordText;
	private static JButton loginButton;
	private static JPanel jPanel;

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				jPanel = new JPanel();
				jFrame = new JFrame("TestRailApp");
				jFrame.setSize(300, 160);
				jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				jFrame.add(jPanel);
				jPanel.setLayout(null);

				createUserNameLabel();
//				createUserNameText();
				createPasswordLabel();
//				createPasswordText();
				createLoginButton();

				jFrame.setVisible(true);
				Toolkit.getDefaultToolkit().beep();
			}
		});
	}

	public static void second() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				names = new ArrayList<String>();
				names = projects();
				jFrame.setVisible(false);
				jPanel2 = new JPanel();
				frame = new JFrame("TestRailApp");
				frame.setSize(360, 290);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(jPanel2);
				jPanel2.setLayout(null);

				createProjectLabel();
				createProjectComboBox();
				createSuiteLabel();
				creatSutieComboBox();
				createSectionLabel();
				creatSectionComboBox();
				createBrowseButton();
				createExcellPathText();
				createUploadButton();
				createProgressBar();

				frame.setVisible(true);
			}
		});
	}

	private static void createLoginButton() {
		loginButton = new JButton("Single Sign On");
		int s = loginButton.getPreferredSize().width;
		loginButton.setBounds(48,80, 180, 25);

		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				driverSetUp();
				userName =""; // userNameText.getText();
				password = ""; //String.valueOf(passwordText.getPassword());
				loginTestRail(userName, password);
				second();
			}
		});
		loginButton.addActionListener(new TestRailApp());
		jPanel.add(loginButton);
	}

	private static void createPasswordText() {
		passwordText = new JPasswordField();
		passwordText.setBounds(100, 50, 180, 25);
		passwordText.setEnabled(false);
		jPanel.add(passwordText);
	}

	private static void createPasswordLabel() {
		passwordLabel = new JLabel("Log into Your Account");
		passwordLabel.setFont(new Font("Tahoma",Font.PLAIN,11));
		int s = passwordLabel.getPreferredSize().width;
		passwordLabel.setBounds((300-s)/2, 50, 100, 25);
		jPanel.add(passwordLabel);
	}

	private static void createUserNameText() {
		userNameText = new JTextField();
		userNameText.setBounds(100, 20, 180, 25);
		userNameText.setEnabled(false);
		jPanel.add(userNameText);
	}

	private static void createUserNameLabel() {
		userNameLabel = new JLabel("TestRail QA");
		userNameLabel.setFont(new Font("Verdana",Font.BOLD,12));
		int s = userNameLabel.getPreferredSize().width;
		userNameLabel.setBounds((300-s)/2-4, 20, 100, 25);

//		Font f = userNameLabel.getFont();
//		userNameLabel.setFont(f.deriveFont(f.getSize2D()));
//		userNameLabel.setFont(f.deriveFont(f.getStyle()|Font.BOLD));
		jPanel.add(userNameLabel);
	}

	public static void loginTestRail(String username, String password) {
		new LoginPageTestRail().login(username, password);
	}

	private static void createProgressBar() {
		progressBar = new JProgressBar(0, 100);
		progressBar.setBounds(20, 210, 310, 25);
		progressBar.setValue(0);
		progressBar.setVisible(true);
		progressBar.setStringPainted(true);
		jPanel2.add(progressBar);
	}

	private static void createUploadButton() {
		uploadButton = new JButton("Upload");
		uploadButton.setBounds(230, 180, 100, 25);
		uploadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ac) {
				try {
					int i = 0;
					int j = 0;
					int x = 101 / paths.size();
					int cnt = x;
					for (String path : paths) {
						if (paths.size() == 1) {
							dashboard.clickAndSelectImprot("Import from CSV").importCsv(path);
							progressBar.setValue(100);
						} else {
							for (i = j; i < 101; i++) {
								waitMiliSeconds(15);
								if (i == cnt) {
									dashboard.clickAndSelectImprot("Import from CSV").importCsv(path);
									if (i > 95) {
										progressBar.setValue(100);
									} else {
										progressBar.setValue(i);
									}
									j = i++;
									cnt = cnt + x;
									break;
								}
								j++;
							}
						}
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Something went wrong when upload csv file!!!", "Error", -1);
					closeFail();
				}
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "CSV files uploaded.");
				close();
			}
		});
		uploadButton.setEnabled(false);
		jPanel2.add(uploadButton);
	}

	private static void createExcellPathText() {
		textField = new JFormattedTextField();
		textField.setBounds(130, 150, 200, 25);
		textField.setEnabled(false);
		jPanel2.add(textField);
	}

	private static void createBrowseButton() {
		browse = new JButton("Browse");
		browse.setBounds(20, 150, 100, 25);
		browse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ac) {
				try {
					final JFileChooser fc = new JFileChooser();
					fc.setMultiSelectionEnabled(true);
					fc.showOpenDialog(null);
					{
						if (!(fc.getSelectedFile() == null)) {
							File[] files = fc.getSelectedFiles();
							int i = 0;
							for (File p : files) {
								paths.add(i, p.getAbsolutePath());
								i++;
							}
							textField.setValue(paths.toString().substring(1, paths.toString().length() - 1));
							textField.requestFocus();
							uploadButton.setEnabled(true);
						}
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Something went wrong when select csv file!!!", "Error", -1);
					closeFail();
				}
			}
		});
		jPanel2.add(browse);

	}

	private static void creatSectionComboBox() {
		section = new JComboBox();
		section.setBounds(130, 100, 200, 25);
		section.setActionCommand("false");
		section.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Boolean.parseBoolean(section.getActionCommand())) {
					try {
						SECTION_NAME = section.getSelectedItem().toString();
						if (SECTION_NAME.equals(""))
							JOptionPane.showMessageDialog(null, "Please select section.", "Warning",
									JOptionPane.WARNING_MESSAGE);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Something went wrong while select suit!!!", "Error", -1);
						closeFail();
					}
				}
			}
		});
		jPanel2.add(section);
	}

	private static void createSectionLabel() {
		JLabel sectionLabel = new JLabel("Section Name");
		sectionLabel.setBounds(20, 100, 100, 25);
		jPanel2.add(sectionLabel);
	}

	private static void creatSutieComboBox() {
		suit = new JComboBox();
		suit.setBounds(130, 60, 200, 25);
		suit.setActionCommand("false");
		suit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Boolean.parseBoolean(suit.getActionCommand())) {
					try {
						SUIT_NAME = suit.getSelectedItem().toString();
						if (SUIT_NAME.equals(""))
							JOptionPane.showMessageDialog(null, "Please select section.", "Warning",
									JOptionPane.WARNING_MESSAGE);
						else {
							dashboard.findSuitsAndClick(SUIT_NAME);
							section.setEnabled(true);
							sectionsName = sections();
							section.removeAllItems();
							section.addItem("");
							for (String text : sectionsName) {
								section.addItem(text);
							}
							section.setActionCommand("true");
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Something went wrong while select section!!!", "Error", -1);
						closeFail();
					}
				}
			}
		});
		jPanel2.add(suit);
	}

	private static void createSuiteLabel() {
		JLabel suitLabel = new JLabel("Suit Name");
		suitLabel.setBounds(20, 60, 100, 25);
		jPanel2.add(suitLabel);
	}

	private static void createProjectComboBox() {
		project = new JComboBox();
		project.setBounds(130, 20, 200, 25);
		project.addItem("");
		for (String text : names) {
			project.addItem(text);
		}

		project.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ac) {
				try {
					PROJECT_NAME = project.getSelectedItem().toString();
					if (PROJECT_NAME.equals("")) {
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null, "Please select project.", "Warning",
								JOptionPane.WARNING_MESSAGE);
					} else {
						dashboard.findProjectAndClick(PROJECT_NAME);
						suit.setEnabled(true);
						suitsName = suits();
						suit.setActionCommand("false");
						suit.removeAllItems();
						suit.addItem("");
						for (String text : suitsName) {
							suit.addItem(text);
						}
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Something went wrong while select project!!!", "Error", -1);
					closeFail();
				}
				suit.setActionCommand("true");
			}
		});
		jPanel2.add(project);
	}

	private static void createProjectLabel() {
		JLabel projectLabel = new JLabel("Project Name");
		projectLabel.setBounds(20, 0, 100, 80);
		jPanel2.add(projectLabel);
	}

	public static List<String> suits() {
		return new Dashboard().findTestSuits();
	}

	public static List<String> sections() {
		return new Dashboard().findTestSections();
	}
	public static List<String> projects() {
		return new Dashboard().findProjects();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
