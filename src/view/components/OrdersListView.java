package view.components;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Queue;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

import model.Order;

public class OrdersListView extends JScrollPane {
	private DefaultListModel<Order> model = new DefaultListModel<>();
	private JList<Order> list;
	private JPopupMenu popupMenu = new JPopupMenu();
	private JMenuItem viewItem = new JMenuItem("View");
	private JButton createOrderBtn;
	private JButton fulfillNextOrderBtn;

	public OrdersListView() {
		JPanel contents = new JPanel();
		contents.setLayout(new BoxLayout(contents, BoxLayout.Y_AXIS));
		contents.setBorder(ControlPanel.PANEL_HORIZONTAL_PADDING);

//		this.setPreferredSize(new Dimension(Integer.MAX_VALUE, this.getSize().height));
		list = new JList<>();
		list.setModel(model);

		viewItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = list.getSelectedIndex();
				if (selectedIndex != -1) {
					Order selectedValue = list.getModel().getElementAt(selectedIndex);
					System.out.println("View selected for: " + selectedValue);
					OrderDialog viewOrder = new OrderDialog(selectedValue);
					viewOrder.setVisible(true);
				}
			}
		});

		popupMenu.add(viewItem);

		list.addMouseListener(new MouseInputAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1) {
					// Show the popup menu at the mouse position
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.getClickCount() == 2) {
					int selectedIndex = list.locationToIndex(e.getPoint());
					if (selectedIndex != -1) {
						Order selectedValue = list.getModel().getElementAt(selectedIndex);
						OrderDialog viewOrder = new OrderDialog(selectedValue);
						viewOrder.setVisible(true);
					}
				}
			}
		});

        createOrderBtn = new JButton("Create order");
        createOrderBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, this.getPreferredSize().height));
        createOrderBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        createOrderBtn.addActionListener((actionEvent) -> {
        	OrderDialog newOrderDialog = new OrderDialog();
        	newOrderDialog.setVisible(true);
        });
        contents.add(createOrderBtn);
        
        fulfillNextOrderBtn = new JButton("Fulfill next");
        fulfillNextOrderBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, this.getPreferredSize().height));
        fulfillNextOrderBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        fulfillNextOrderBtn.addActionListener((actionEvent) -> {
        	app.App.controller.fulfillNextOrder();
        });
        contents.add(fulfillNextOrderBtn);
        
		contents.add(list);
		contents.add(Box.createVerticalGlue()); // Moves all elements to the top if possible
		this.setViewportView(contents);
	}

	public DefaultListModel<Order> getListModel() {
		return this.model;
	}

	public void updateListModel(Queue<Order> orders) {
		model.clear();
		model.addAll(orders);
		list.setModel(model);
	}

}
