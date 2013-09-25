package com.jsandusky.jrpgexample;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

import javax.naming.Reference;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import com.jsandusky.gdx.util.DataObject;
import com.jsandusky.jrpg.model.Base;
import com.jsandusky.util.Spite;

//lots of of different constructors 
public class AutoForm extends JPanel {
	private static final long serialVersionUID = 1L;
	Object target_;
	HashMap<Field, Component> comps;
	HashMap<JButton, Field> buttons = new HashMap<JButton,Field>();
	ArrayList<String> exclusive = new ArrayList<String>();
	int begin = 0;
	int end = Integer.MAX_VALUE;
	
	public void setBounds(int b, int e) {
		begin = b;
		end = e;
	}
	
	public AutoForm() {
		super();
		setAlignmentY(this.TOP_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}
	
	public AutoForm(Object t) {
		setTarget(t);
	}
	
	public AutoForm(ArrayList<String> excludeTo) {
		exclusive = excludeTo;
	}
	
	public AutoForm(ArrayList<String> ex, DataObject obj) {
		exclusive = ex;
		setTarget(obj);
	}
	
	public AutoForm(String excludeTo) {
		exclusive.add(excludeTo);
	}
	
	public AutoForm(String ex, DataObject obj) {
		exclusive.add(ex);
		setTarget(obj);
	}
	
	public void setTarget(Object obj) {
		target_ = obj;
		clearForm();
		this.validate();
		this.invalidate();
		if (target_ != null)
			populateForm();
		this.validate();
		this.invalidate();
	}
	
	void clearForm() {
		this.removeAll();
	}
	
	public void saveChanges() {
		Set<Field> keys = comps.keySet();
		for (Field f : keys) {
			Component c = comps.get(f);
			try {
				if (f.getType() == int.class) {
					JSpinner s = (JSpinner)c;
					f.setInt(target_, (Integer)s.getValue());
				} else if (f.getType() == float.class) {
					JSpinner s = (JSpinner)c;
					f.setFloat(target_, (Float)s.getValue());
				} else if (f.getType() == boolean.class) {
					JCheckBox cb = (JCheckBox)c;
					f.set(target_, cb.isSelected());
				} else if (f.getType() == String.class) {
					JTextField tf = (JTextField)c;
					f.set(target_, tf.getText());
				} else if (f.getType().isEnum()) {
					//combobox
				} else if (Reference.class.isAssignableFrom(f.getType())) {//reference
					
				} else if (Base.class.isAssignableFrom(f.getType())) {
					
				}
			} catch (Exception e) {
				
			}
		}
	}
	
	void populateForm() {
		comps = new HashMap<Field,Component>();
		Field[] fields = target_.getClass().getFields();
		
		//sorting three times, sort comp, seems to quit after first pass?
		Arrays.sort(fields, new FieldCompare());
		Arrays.sort(fields, new FieldCompare());
		Arrays.sort(fields, new FieldCompare());
		Arrays.sort(fields, new FieldCompare());
		Arrays.sort(fields, new FieldCompare());
		Arrays.sort(fields, new FieldCompare());
		Arrays.sort(fields, new FieldCompare());
		Arrays.sort(fields, new FieldCompare());
		Arrays.sort(fields, new FieldCompare());
		int ct = 0;
		
		Spite lastAnnote = null;
		for (Field f : fields) {
			/*if (!handleCheck(f))
				continue;*/
			if (ct < begin || ct > end) {
				++ct;
				continue;
			}
			++ct;
			if (!show(f.getName()))
				continue;
			try {
				Spite curAnnote = f.getAnnotation(Spite.class);
				if (curAnnote != null) {
					if (lastAnnote != null) {
						if (curAnnote.group().compareToIgnoreCase(lastAnnote.group()) != 0) {
							lastAnnote = curAnnote;
							JLabel glbl = new JLabel();
							glbl.setText(curAnnote.group());
							glbl.setFont(new Font(glbl.getFont().getName(), Font.BOLD, glbl.getFont().getSize()));
							add(glbl);
						}
					} else {
						lastAnnote = curAnnote;
						JLabel glbl = new JLabel();
						glbl.setText(curAnnote.group());
						glbl.setFont(new Font(glbl.getFont().getName(), Font.BOLD, glbl.getFont().getSize()));
						add(glbl);
					}
				}
				if (f.getType() == int.class) {
					JSpinner spinner = new JSpinner();
					spinner.setModel(new SpinnerNumberModel(new Integer(0), null, null, new Integer(1)));
					Dimension sz = spinner.getPreferredSize();
					sz.width = 100;
					//sz.height = 20;
					spinner.setMaximumSize(sz);
					spinner.setAlignmentX(LEFT_ALIGNMENT);
					spinner.setValue(getIValue(f));
					if (f.getAnnotation(Spite.class) != null && f.getAnnotation(Spite.class).tip().length() > 0)
						spinner.setToolTipText(f.getAnnotation(Spite.class).tip());
					addLabel(f);
					add(spinner);
					comps.put(f,  spinner);
				} else if (f.getType() == float.class) {
					JSpinner spinner = new JSpinner();
					spinner.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(0.25)));
					Dimension sz = spinner.getPreferredSize();
					sz.width = 100;
					//sz.height = 20;
					spinner.setMaximumSize(sz);
					spinner.setAlignmentX(LEFT_ALIGNMENT);
					spinner.setValue(this.getFValue(f));
					if (f.getAnnotation(Spite.class) != null && f.getAnnotation(Spite.class).tip().length() > 0)
						spinner.setToolTipText(f.getAnnotation(Spite.class).tip());
					addLabel(f);
					add(spinner);
					comps.put(f,  spinner);
				} else if (f.getType() == String.class) {
					JTextArea tField = new JTextArea();
					Object o = f.get(target_);
					tField.setText(o != null ? o.toString() : "");
					tField.setAlignmentX(LEFT_ALIGNMENT);
					tField.setAutoscrolls(true);
					//Dimension sz = tField.getMaximumSize();
					//sz.width = 250;
					//sz.height = 60;
					JScrollPane fuckingGay = new JScrollPane();
					fuckingGay.setAlignmentX(LEFT_ALIGNMENT);
					fuckingGay.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
					//tField.setMaximumSize(sz);
					fuckingGay.setViewportView(tField);
					if (f.getAnnotation(Spite.class) != null && f.getAnnotation(Spite.class).tip().length() > 0)
						tField.setToolTipText(f.getAnnotation(Spite.class).tip());
					addLabel(f);
					add(fuckingGay);
					//add(Box.createVerticalGlue());
					comps.put(f, tField);
				} else if (f.getType() == boolean.class) {
					JCheckBox cb = new JCheckBox();
					cb.setText(buildFormLabel(f.getName()));
					cb.setSelected(f.getBoolean(target_));
					if (f.getAnnotation(Spite.class) != null)
						cb.setToolTipText(f.getAnnotation(Spite.class).tip());
					add(cb);
					comps.put(f,  cb);
				} 
				//add(Box.createVerticalGlue());
			} catch (Exception e) {
				
			}
		}
		
		this.validate();
		this.invalidate();
	}
	void addLabel(Field f) {
		JLabel lbl = new JLabel();
		lbl.setAlignmentX(LEFT_ALIGNMENT);
		lbl.setText(buildFormLabel(f.getName()));
		if (f.getAnnotation(Spite.class) != null && f.getAnnotation(Spite.class).tip().length() > 0)
			lbl.setToolTipText(f.getAnnotation(Spite.class).tip());
		add(lbl);
	}
	
	public boolean show(String fieldName) {
		if (fieldName.charAt(0) == '_')
			return false;
		return true;
	}
	
	public static boolean isUppercase(String c) {
		String v = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		if (v.contains(c))
			return true;
		return false;
	}
	
	public static String buildFormLabel(String fieldName) {
		String ret = "";
		for (char c : fieldName.toCharArray()) {
			if (ret.length() > 0) {
				String check = "" + c;
				if (isUppercase(check)) {
					ret += " ";
				}
				ret += c;
			} else
				ret += c;
		}
		ret = ret.replace("_", " "); //turn underscores into spaces
		return ret;
	}
	
	boolean handleCheck(Field f) {
		try {
			ArrayList<Class> canHandle = new ArrayList<Class>();
			canHandle.add(int.class);
			canHandle.add(float.class);
			canHandle.add(boolean.class);
			canHandle.add(String.class);
			
			if (canHandle.contains(f.getType()))
				return true;
			if (f.getType() == DataObject.class) {
				DataObject o = (DataObject)f.getType().newInstance();
				return o.freestanding();				
			} else if (f.getType().isEnum())
				return true;
		} catch (Exception e) {
			
		}
		return true;
	}
	
	class ButtonManager implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton button = (JButton)arg0.getSource();
			Field f = AutoForm.this.buttons.get(button);
		}
	}
	
	float getFValue(Field f) {
		try {
			return f.getFloat(target_);
		} catch (Exception e ) {}
		return 0;
	}
	
	int getIValue(Field f) {
		try {
			return f.getInt(target_);
		} catch (Exception e ) {}
		return 0;
	}
	
	String getSValue(Field f) {
		try {
			return f.get(target_).toString();
		} catch (Exception e ) {}
		return "";
	}
}
