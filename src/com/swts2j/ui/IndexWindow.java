package com.swts2j.ui;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.swts2j.common.utils.KeyCode;
import com.swts2j.common.utils.Selenium2Java;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class IndexWindow {

	protected Shell shell;
	private Text txtUserName;
	private Text txtUserPwd;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			IndexWindow window = new IndexWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		Rectangle bounds = Display.getDefault().getPrimaryMonitor().getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(666, 416);
		shell.setText("简历自动下载器");
		shell.setLayout(new FormLayout());

		Label lblNewLabel = new Label(shell, SWT.NONE);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(0, 35);
		fd_lblNewLabel.left = new FormAttachment(0, 257);
		fd_lblNewLabel.right = new FormAttachment(0, 381);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.BOLD));
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setText("获取51job简历");

		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		FormData fd_lblNewLabel_1 = new FormData();
		fd_lblNewLabel_1.top = new FormAttachment(0, 117);
		lblNewLabel_1.setLayoutData(fd_lblNewLabel_1);
		lblNewLabel_1.setText("51job用户名：");

		txtUserName = new Text(shell, SWT.BORDER);
		txtUserName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.keyCode;
				if (keyCode == KeyCode.ENTER || keyCode == KeyCode.SMALL_KEY_BOARD_ENTER) {
					sendInfo();
				}
			}
		});
		fd_lblNewLabel_1.left = new FormAttachment(txtUserName, -125, SWT.LEFT);
		fd_lblNewLabel_1.right = new FormAttachment(txtUserName, -26);
		FormData fd_txtUserName = new FormData();
		fd_txtUserName.top = new FormAttachment(lblNewLabel, 58);
		fd_txtUserName.right = new FormAttachment(100, -87);
		fd_txtUserName.left = new FormAttachment(0, 216);
		txtUserName.setLayoutData(fd_txtUserName);

		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		FormData fd_lblNewLabel_2 = new FormData();
		fd_lblNewLabel_2.top = new FormAttachment(lblNewLabel_1, 75);
		fd_lblNewLabel_2.left = new FormAttachment(lblNewLabel_1, 0, SWT.LEFT);
		lblNewLabel_2.setLayoutData(fd_lblNewLabel_2);
		lblNewLabel_2.setText("51job用户密码：");

		txtUserPwd = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		txtUserPwd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.keyCode;
				if (keyCode == KeyCode.ENTER || keyCode == KeyCode.SMALL_KEY_BOARD_ENTER) {
					sendInfo();
				}
			}
		});
		FormData fd_txtUserPwd = new FormData();
		fd_txtUserPwd.right = new FormAttachment(txtUserName, 0, SWT.RIGHT);
		fd_txtUserPwd.top = new FormAttachment(txtUserName, 69);
		fd_txtUserPwd.left = new FormAttachment(txtUserName, 0, SWT.LEFT);
		txtUserPwd.setLayoutData(fd_txtUserPwd);

		Button btnGet = new Button(shell, SWT.NONE);
		btnGet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.keyCode;
				if (keyCode == KeyCode.ENTER || keyCode == KeyCode.SMALL_KEY_BOARD_ENTER) {
					sendInfo();
				}
			}
		});
		btnGet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sendInfo();
			}
		});
		FormData fd_btnGet = new FormData();
		fd_btnGet.bottom = new FormAttachment(100, -54);
		fd_btnGet.right = new FormAttachment(txtUserName, 0, SWT.RIGHT);
		btnGet.setLayoutData(fd_btnGet);
		btnGet.setText("获取简历");

	}

	private void sendInfo() {
		String userName = txtUserName.getText().trim();
		String userPwd = txtUserPwd.getText().trim();
		if (StringUtils.isBlank(userName)) {
			MessageBox infoBox = new MessageBox(shell, SWT.ICON_INFORMATION);
			infoBox.setText("提示信息");
			infoBox.setMessage("用户名不能为空！");
			infoBox.open();
			txtUserName.setFocus();
			return;
		}
		if (StringUtils.isBlank(userPwd)) {
			MessageBox infoBox = new MessageBox(shell, SWT.ICON_INFORMATION);
			infoBox.setText("提示信息");
			infoBox.setMessage("用户密码不能为空！");
			infoBox.open();
			txtUserPwd.setFocus();
			return;
		}
		Selenium2Java.get51JobCV(System.getProperty("user.dir") + File.separator + "drivers", userName, userPwd);
		shell.close();
	}
}
