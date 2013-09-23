package com.jsandusky.util;

import java.util.ArrayList;

public class MsgLog {
	public static class Msg {
		int lvl;
		String msg;
		long time;
	}
	ArrayList<MsgLogListener> listeners_ = new ArrayList<MsgLogListener>();
	public ArrayList<Msg> msgs = new ArrayList<Msg>(256);

	static MsgLog inst_;
	public static MsgLog inst() {
		if (inst_ == null)
			inst_ = new MsgLog();
		return inst_;
	}
	void msg_(String msg, int lvl) {
		Msg m = new Msg();
		m.lvl = lvl;
		m.msg = msg;
		m.time = java.lang.System.currentTimeMillis(); 
		msgs.add(m);
	}
	public void addListener(MsgLogListener l) {
		listeners_.add(l);
	}
	
	public void unlisten(MsgLogListener l) {
		listeners_.remove(l);
	}
	
	public void error(String msg) {
		msg_(msg,4);		
	}

	public void warning(String msg) {
		msg_(msg,3);
	}
	public void info(String msg) {
		msg_(msg,2);
	}
	public void notice(String msg) {
		msg_(msg,1);
	}
	public void clear() {
		msgs.clear();
	}

	public String getHTML(int minLevel, boolean aNew) {
		StringBuilder sb = new StringBuilder();
        if (aNew) {
            sb.append("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html\"; charset=\"utf-8\" />");
            sb.append("<title>Profile Dump</title><style type=\"text/css\">");

            sb.append("body, html {\n");
            sb.append("background: #000000;\n");
            sb.append("width: 100%;\n");
            sb.append("font-family: Arial;\n");
            sb.append("font-size: 16px;\n");
            sb.append("color: #C0C0C0;\n");
            sb.append("}\n");

            sb.append("h1 {\n");
            sb.append("color : #FFFFFF;\n");
            sb.append("border-bottom : 1px dotted #888888;\n");
            sb.append("}\n");

            sb.append("pre {\n");
            sb.append("font-family : arial;\n");
            sb.append("margin : 0;\n");
            sb.append("}\n");

            sb.append(".box {\n");
            sb.append("border : 1px dotted #818286;\n");
            sb.append("padding : 5px;\n");
            sb.append("margin: 5px;\n");
            sb.append("width: 100%;\n");
            sb.append("background-color : #292929;\n");
            sb.append("}\n");

            sb.append(".lvl4 {\n");
            sb.append("color: #EE1100;\n");
            sb.append("font-weight: bold\n");
            sb.append("}\n");

            sb.append(".lvl3 {\n");
            sb.append("color: #FFCC00;\n");
            sb.append("font-weight: bold\n");
            sb.append("}\n");

            sb.append(".lvl2 {\n");
            sb.append("color: #C0C0C0;\n");
            sb.append("}\n");

            sb.append(".lvl1 {\n");
            sb.append("color: #CCA0A0;\n");
            sb.append("}\n");


            sb.append("</style>");
            sb.append("</head>");
            sb.append("<body>");

            sb.append("<h1>Debug Log</h1>\n");
            sb.append("<div class=\"box\">\n");
            sb.append("<table>\n");
        }

		int writeCt = 0;
		for (int i = 0; i < msgs.size(); ++i) {
			Msg m = msgs.get(i);
			if (m.lvl < minLevel)
				continue;
			++writeCt;
            sb.append("<tr><td>");
            sb.append(m.time);
            sb.append("</td></tr>");
			sb.append("<tr><td class='lvl" + m.lvl + ">");
			sb.append(m.msg);
            sb.append("</td></tr>\n");
		}
        msgs.clear();
		return sb.toString();
	}

	public String getString(int minLevel) {
		String ret = "";
		int writeCt = 0;
		for (int i = 0; i < msgs.size(); ++i) {
			Msg m = msgs.get(i);
			if (m.lvl < minLevel)
				continue;
			if (writeCt > 0)
				ret += "\n";
			++writeCt;
			ret += m.time + ": ";
			switch (m.lvl) {
			case 1:
				ret += "Notice\n";
				break;
			case 2:
				ret += "Info\n";
				break;
			case 3:
				ret += "---WARNING---\n";
				break;
			case 4:
				ret += "***ERROR***\n";
				break;
			}
			ret += m.msg;
		}
        msgs.clear();
		return ret;
	}
}
