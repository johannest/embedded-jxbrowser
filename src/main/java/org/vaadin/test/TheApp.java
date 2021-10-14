package org.vaadin.test;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.cookie.Cookie;
import com.teamdev.jxbrowser.view.swing.BrowserView;

import javax.swing.*;
import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class TheApp {

    public static void main(String[] args) {
        JxBrowserOptionsBuilder builder = new JxBrowserOptionsBuilder();
        Browser browser = builder.getEngine().newBrowser();
        BrowserView browserView = BrowserView.newInstance(browser);
        browserView.setPreferredSize(new Dimension(1280, 800));
        browser.navigation().loadUrl("http://localhost:8080/simple");

        JFrame frame = new JFrame("Embedded JxBrowser demo");
        frame.setLayout(new BorderLayout());

        JPanel browserPanel = new JPanel(new FlowLayout());
        browserPanel.add(browserView);
        JButton showButton = new JButton("Show browser");
        showButton.addActionListener(e -> {
            browserPanel.setVisible(true);
        });
        JButton hideButton = new JButton("Hide browser");
        hideButton.addActionListener(e -> {
            browserPanel.setVisible(false);
        });
        JButton simpleButton = new JButton("Simple form");
        simpleButton.addActionListener(e -> {
            browser.navigation().loadUrl("http://localhost:8080/simple");
        });
        JButton complexButton = new JButton("Complex form");
        complexButton.addActionListener(e -> {
            browser.navigation().loadUrl("http://localhost:8080/complex");
        });
        JTextField textField = new JTextField("Some text to be sent to Vaadin application");
        textField.setColumns(30);
        JButton sendButton = new JButton("Send text with URL parameter");
        sendButton.addActionListener(e -> {
            browser.navigation().loadUrl("http://localhost:8080/complex?data=" + textField.getText());
        });
        JButton receiveButton = new JButton("Receive text from the cookie");
        receiveButton.addActionListener(e -> {
            for (Cookie cookie : browser.engine().cookieStore().cookies()) {
                if (cookie.name().contains("demo-data-cookie")) {
                    try {
                        String decodedValue = URLDecoder.decode(cookie.value(), "UTF-8");
                        textField.setText(decodedValue);
                    } catch (UnsupportedEncodingException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(showButton);
        buttonPanel.add(hideButton);
        buttonPanel.add(simpleButton);
        buttonPanel.add(complexButton);
        buttonPanel.add(textField);
        buttonPanel.add(sendButton);
        buttonPanel.add(receiveButton);
        frame.add(buttonPanel, BorderLayout.PAGE_START);
        frame.add(browserPanel, BorderLayout.CENTER);
        frame.setPreferredSize(new Dimension(1400, 900));
        frame.setMinimumSize(new Dimension(1400, 900));
        frame.pack();
        frame.setVisible(true);
    }
}