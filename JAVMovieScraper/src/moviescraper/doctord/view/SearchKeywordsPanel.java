package moviescraper.doctord.view;

import moviescraper.doctord.controller.UtilityFunctions;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;


public class SearchKeywordsPanel extends JPanel implements ItemListener {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldTag;
	private ArrayList<JCheckBox> tagCheckBoxes;
	private List<String> originalTagList; //original values in case we hit cancel
	public List<String> editedTagList; //edited values which become active when we hit ok

	private static final int DEFAULT_GENRE_LENGTH = 25;

	@SuppressWarnings("unchecked")
	public SearchKeywordsPanel(final List<String> tagList){
		super(new BorderLayout());
		this.originalTagList = tagList;
		editedTagList = (List<String>) UtilityFunctions.cloneObject(originalTagList);
	
		super.setMaximumSize(new Dimension(400,200));
		final JPanel currentMovieTagsPanel = new JPanel(new ModifiedFlowLayout());
		
		Border panelBorder = BorderFactory.createEtchedBorder();
		//JPanel favoriteMovieTagsPanel = new JPanel();
		//favoriteMovieTagsPanel.setLayout(new BoxLayout(favoriteMovieTagsPanel, BoxLayout.Y_AXIS));
		JPanel enterANewTagPanel = new JPanel();
		//enterANewTagPanel.setBorder(blackline);
		

		//enter a new tag panel
		JLabel lblTag = new JLabel("Edit Search Keywords for the Current Movie: ");
		JButton addNewTagButton = new JButton("Add");
		
		//Adds a new checkbox, in the checked state. Also adds this to the list of tags
		ActionListener addTagActionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if(textFieldTag != null && textFieldTag.getText()!= null && textFieldTag.getText().length() > 0 && editedTagList != null){
					String tagToAdd = new String(textFieldTag.getText());
					if(!editedTagList.contains(tagToAdd))
					{
						editedTagList.add(tagToAdd);
						addTagCheckBoxToPanel(currentMovieTagsPanel, tagToAdd, true);
						if(textFieldTag != null)
						{
							textFieldTag.setText("");
							textFieldTag.requestFocus();
						}
					}
				}
			}
		};

		//Was disabled for some odd reason
		addNewTagButton.addActionListener(addTagActionListener);
		
		enterANewTagPanel.add(lblTag);
		textFieldTag = new JTextField(DEFAULT_GENRE_LENGTH);
		textFieldTag.addActionListener(addTagActionListener);
		enterANewTagPanel.add(textFieldTag);
		enterANewTagPanel.add(addNewTagButton);
		
		//existing tags panel
		tagCheckBoxes = new ArrayList<>(editedTagList.size());
		//get all the labels of the check boxes set up
		for (String currentTagToAdd : editedTagList) {
			addTagCheckBoxToPanel(currentMovieTagsPanel, currentTagToAdd, true);
		}

		//scroll panes
		final JScrollPane currentMovieTagsPanelScrollPane = new JScrollPane(currentMovieTagsPanel);
		//currentMovieTagsPanelScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		currentMovieTagsPanelScrollPane.setBorder(BorderFactory.createEmptyBorder());
		currentMovieTagsPanelScrollPane.setPreferredSize(new Dimension(400, 310));
		
		JPanel westPanel = new JPanel(new BorderLayout());
		westPanel.setBorder(panelBorder);
		JPanel eastPanel = new JPanel(new BorderLayout());
		eastPanel.setBorder(panelBorder);
		
		
		
		westPanel.add(enterANewTagPanel, BorderLayout.NORTH);
		
		westPanel.add(currentMovieTagsPanelScrollPane, BorderLayout.CENTER);
		add(westPanel, BorderLayout.WEST);
	}
	
	private void addTagCheckBoxToPanel(JPanel panel, String tag, boolean initialStatus)
	{
		JCheckBox checkBoxOfTag = new JCheckBox(tag);
		checkBoxOfTag.setSelected(initialStatus);
		checkBoxOfTag.addItemListener(this);
		panel.add(checkBoxOfTag);
		//Monitor: SMall change seemed to help the JPanel update
		panel.invalidate();
		panel.revalidate();
	}
	
	//What happens when a checkbox in the tag editor is checked or unchecked
	@Override
	public void itemStateChanged(ItemEvent event) {
		if(tagCheckBoxes != null && editedTagList != null)
		{
			JCheckBox eventItem = (JCheckBox) event.getItem();
			String tagNameToEdit = new String(eventItem.getText());
			if(event.getStateChange() == ItemEvent.SELECTED)
			{
				if(!editedTagList.contains(tagNameToEdit))
				{
					editedTagList.add(tagNameToEdit);
				}
			}
			else if(event.getStateChange() == ItemEvent.DESELECTED)
			{
				editedTagList.remove(tagNameToEdit);
			}
		}
	}
	
	/**
	 * Finalize changes made
	 */
	public void save()
	{
		originalTagList.clear();
		//originalTagList.addAll(editedTagList);
		originalTagList = editedTagList;
	}
}
