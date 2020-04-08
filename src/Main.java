import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Base64;

public class Main {
    static JFrame jfrm = new JFrame("转换咖啡");
    static Container ct = jfrm.getContentPane();
    static Image im = Toolkit.getDefaultToolkit().getImage("/icon.jpg");

    static JLabel writer1 = new JLabel("made by ku3n|李香兰");
    static JLabel email1 = new JLabel("ku3n@qq.com");
    static JLabel writer2 = new JLabel("made by ku3n|李香兰");
    static JLabel email2 = new JLabel("ku3n@qq.com");

    static JLabel lab_2 = new JLabel("二进制：");
    static JLabel lab_4 = new JLabel("四进制：");
    static JLabel lab_10 = new JLabel("十进制：");
    static JLabel lab_8 = new JLabel("八进制：");
    static JLabel lab_16 = new JLabel("十六进制：");
    static JLabel lab_32 = new JLabel("三十二进制：");

    static JLabel lab_url = new JLabel("URL编码");
    static JLabel lab_utf = new JLabel("UTF-8编码");
    static JLabel lab_ascii = new JLabel("ASCII编码");
    static JLabel lab_base64 = new JLabel("Base64编码");
    static JLabel lab_unicode = new JLabel("Unicode编码");

    static JTextField jtf_2 = new JTextField(50);
    static JTextField jtf_4 = new JTextField(50);
    static JTextField jtf_8 = new JTextField(50);
    static JTextField jtf_10 = new JTextField(50);
    static JTextField jtf_16 = new JTextField(50);
    static JTextField jtf_32 = new JTextField(50);

    static JButton jb_2 = new JButton("二进制转！");
    static JButton jb_4 = new JButton("四进制转！");
    static JButton jb_8 = new JButton("八进制转！");
    static JButton jb_10 = new JButton("十进制转！");
    static JButton jb_16 = new JButton("十六进制转！");
    static JButton jb_32 = new JButton("三十二进制转！");

    static JButton jb_decode = new JButton("进行解码");
    static JButton jb_encode = new JButton("进行编码");
    static JTextField jTextField_src = new JTextField("在此输入想要编码或者解码的内容");
    static JTextField jTextField_unicode = new JTextField();
    static JTextField jTextField_url = new JTextField();
    static JTextField jTextField_utf = new JTextField();
    static JTextField jTextField_ascii = new JTextField();
    static JTextField jTextField_base64 = new JTextField();



    static JTabbedPane tp = new JTabbedPane();
    static JPanel jpa_system = new JPanel();
    static JPanel jpa_trans = new JPanel();

    static double toDouble(char x) {
        if(x >= '0' && x<='9')
            return x - '0';
        else if(x >= 'a' && x <= 'z')
            return x - 'a' + 10 ;
        else
            return x -'A' + 10;
    }

    static char toChar(int x) {
        char x1;
        if(x >= 0 && x <= 9) {
            x1 = (char)(x + 48);
        }
        else {
            int z = x - 10;
            x1 = (char)(z + 65);
        }
        return x1;
    }

    static String transRadix(String str, int srcNum, int desNum) {
        char[] chs = new char[str.length()];
        chs = str.toCharArray();
        double product = 1.0;    // 进制的幂
        double deca = 0;       // 存为十进制中间转换
        for(int i = chs.length - 1; i >= 0; i--) {
            char ch = chs[i];
            deca += toDouble(ch) * product;
            product *= srcNum;
        }
        char[] relusts = new char[100];
        String relStr = "";
        int num = 0;
        do {
            double reout = deca % desNum;
            relusts[num++] = toChar((int)reout);
            deca = deca / desNum;
        }while(deca >= 1);          // 当十进制中间数大于1时继续计算
        relStr = relStr.valueOf(relusts,0,num);
        relStr.trim();
        StringBuffer strBuf = new StringBuffer(relStr);
        relStr = strBuf.reverse().toString();
        return relStr;
    }

    static class myLisenter implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String str = actionEvent.getActionCommand();
            String str2;
            double num;
            if (str.equals(jb_2.getText())) {
                str2 = jtf_2.getText();
                jtf_4.setText(transRadix(str2,2,4));
                jtf_8.setText(transRadix(str2,2,8));
                jtf_10.setText(transRadix(str2,2,10));
                jtf_16.setText(transRadix(str2,2,16));
                jtf_32.setText(transRadix(str2,2,32));
            }
            else if(str.equals(jb_4.getText())) {
                str2 = jtf_4.getText();
                jtf_2.setText(transRadix(str2,4,2));
                jtf_8.setText(transRadix(str2,4,8));
                jtf_10.setText(transRadix(str2,4,10));
                jtf_16.setText(transRadix(str2,4,16));
                jtf_32.setText(transRadix(str2,4,32));
            }
            else if(str.equals(jb_8.getText())) {
                str2 = jtf_8.getText();
                jtf_2.setText(transRadix(str2,8,2));
                jtf_4.setText(transRadix(str2,8,4));
                jtf_10.setText(transRadix(str2,8,10));
                jtf_16.setText(transRadix(str2,8,16));
                jtf_32.setText(transRadix(str2,8,32));
            }
            else if(str.equals(jb_10.getText())) {
                str2 = jtf_10.getText();
                jtf_2.setText(transRadix(str2,10,2));
                jtf_4.setText(transRadix(str2,10,4));
                jtf_8.setText(transRadix(str2,10,8));
                jtf_16.setText(transRadix(str2,10,16));
                jtf_32.setText(transRadix(str2,10,32));
            }
            else if(str.equals(jb_16.getText())) {
                str2 = jtf_16.getText();
                jtf_2.setText(transRadix(str2,16,2));
                jtf_4.setText(transRadix(str2,16,4));
                jtf_8.setText(transRadix(str2,16,8));
                jtf_10.setText(transRadix(str2,16,10));
                jtf_32.setText(transRadix(str2,16,32));
            }
            else if(str.equals(jb_32.getText())) {
                str2 = jtf_32.getText();
                jtf_2.setText(transRadix(str2,32,2));
                jtf_4.setText(transRadix(str2,32,4));
                jtf_8.setText(transRadix(str2,32,8));
                jtf_10.setText(transRadix(str2,32,10));
                jtf_16.setText(transRadix(str2,32,16));
            }
            else if(str.equals(jb_encode.getText())) {
                str2 = jTextField_src.getText();
                jTextField_ascii.setText(asciiEncode(str2));
                jTextField_base64.setText(baseEncode(str2));
                jTextField_unicode.setText(unicodeEncode(str2));
                jTextField_url.setText(urlEncode(str2));
                jTextField_utf.setText(utfEncode(str2));
            }
            else if(str.equals(jb_decode.getText())) {
                str2 = jTextField_src.getText();
                try {
                    jTextField_ascii.setText(asciiDecode(str2));
                }catch (Exception e) {
                    jTextField_ascii.setText("ASCII码转码失败");
                    System.out.println(e);
                }
                try {
                    jTextField_base64.setText(baseDecode(str2));
                }catch (Exception e) {
                    System.out.println(e);
                }
                try {
                    jTextField_unicode.setText(unicodeDecode(str2));
                }catch (Exception e) {
                    jTextField_unicode.setText("Unicode转码失败");
                    System.out.println(e);
                }
                try {
                    jTextField_url.setText(urlDecode(str2));
                }catch (Exception e) {
                    jTextField_url.setText("URL转码失败");
                    System.out.println(e);
                }
                try {
                    jTextField_utf.setText(utfDecode(str2));
                }catch (Exception e) {
                    jTextField_utf.setText("UTF-8转码失败");
                    System.out.println(e);
                }
            }
        }
    }

    static String unicodeEncode(String strSrc) {
        StringBuffer output = new StringBuffer();
        for (int i = 0; i < strSrc.length(); i++)
        {
            output.append("\\u" +Integer.toString(strSrc.charAt(i), 16));
        }
        return output.toString();
    }
    static String unicodeDecode(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            }
            else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
  }

    static String utfEncode(String strSrc) {
        StringBuffer output = new StringBuffer();
        for (int i = 0; i < strSrc.length(); i++)
        {
            output.append("&#x" + Integer.toString(strSrc.charAt(i), 16) +  ";");
        }
        return output.toString();
    }
    static String utfDecode(String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("&#x", start + 3);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 3, dataStr.length());
                charStr = charStr.replaceAll(";", "");
            }
            else {
                charStr = dataStr.substring(start + 3, end);
                charStr = charStr.replaceAll(";", "");
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }

    static String asciiEncode(String strSrc) {
        StringBuilder sb = new StringBuilder();
        char[] ch = strSrc.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            sb.append(Integer.valueOf(ch[i]).intValue()).append(" ");
        }
        String[] chars = sb.toString().split(" ");
        StringBuffer strOut = new StringBuffer("\\u");
        for (int i = 0; i < chars.length; i++) {
            strOut.append(chars[i]);
            if(i == chars.length - 1)
                break;
            strOut.append("\\u");
        }
        return strOut.toString();
    }

    static String asciiDecode(String strDes) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = strDes.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = strDes.substring(start + 2, strDes.length());
            }
            else {
                charStr = strDes.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 10); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }
    public static String urlEncode(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String urlDecode(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    static String baseEncode(String strSrc) {
        Base64.Encoder encoder = Base64.getEncoder();
        String encodedText = "Base64加密失败";
        try{
            byte[] textByte = strSrc.getBytes("UTF-8");
            encodedText = encoder.encodeToString(textByte);
        }catch (Exception e) {
            System.out.println(e);
        }
        return encodedText;
    }

    static String baseDecode(String strSrc) {
        Base64.Decoder decoder = Base64.getDecoder();
        String decodedText = "Base64解密失败";
        try{
        decodedText = (new String(decoder.decode(strSrc), "UTF-8"));
        }catch (Exception e){
            System.out.println(e);
        }
        return decodedText;
    }


    public static void main(String[] args) {
        jfrm.setSize(515,440);
        jfrm.setIconImage(im);
        ct.setLayout(null);
        jpa_system.setLayout(null);
        jpa_trans.setLayout(null);

        lab_2.setBounds(20,10,80,20);
        lab_4.setBounds(20,60,80,20);
        lab_8.setBounds(20,110,80,20);
        lab_10.setBounds(20,160,80,20);
        lab_16.setBounds(20,210,80,20);
        lab_32.setBounds(20,260,80,20);

        jtf_2.setBounds(100,10,250,20);
        jtf_4.setBounds(100,60,250,20);
        jtf_8.setBounds(100,110,250,20);
        jtf_10.setBounds(100,160,250,20);
        jtf_16.setBounds(100,210,250,20);
        jtf_32.setBounds(100,260,250,20);

        jb_2.setBounds(360,10,110,20);
        jb_4.setBounds(360,60,110,20);
        jb_8.setBounds(360,110,110,20);
        jb_10.setBounds(360,160,110,20);
        jb_16.setBounds(360,210,110,20);
        jb_32.setBounds(360,260,110,20);

        jpa_system.add(lab_2);
        jpa_system.add(lab_4);
        jpa_system.add(lab_8);
        jpa_system.add(lab_10);
        jpa_system.add(lab_16);
        jpa_system.add(lab_32);

        jpa_system.add(jtf_2);
        jpa_system.add(jtf_4);
        jpa_system.add(jtf_8);
        jpa_system.add(jtf_10);
        jpa_system.add(jtf_16);
        jpa_system.add(jtf_32);

        jpa_system.add(jb_2);
        jpa_system.add(jb_4);
        jpa_system.add(jb_8);
        jpa_system.add(jb_10);
        jpa_system.add(jb_16);
        jpa_system.add(jb_32);

        jb_2.addActionListener(new myLisenter());
        jb_4.addActionListener(new myLisenter());
        jb_8.addActionListener(new myLisenter());
        jb_10.addActionListener(new myLisenter());
        jb_16.addActionListener(new myLisenter());
        jb_32.addActionListener(new myLisenter());



        jb_encode.setBounds(10,15,90,20);
        jb_decode.setBounds(10,40,90,20);
        jTextField_src.setBounds(110,10,350,50);
        jTextField_base64.setBounds(110,60,350,50);
        jTextField_unicode.setBounds(110,110,350,50);
        jTextField_url.setBounds(110,160,350,50);
        jTextField_utf.setBounds(110,210,350,50);
        jTextField_ascii.setBounds(110,260,350,50);

        lab_base64.setBounds(20,70,80,40);
        lab_unicode.setBounds(20,120,80,40);
        lab_url.setBounds(20,170,80,40);
        lab_utf.setBounds(20,220,80,40);
        lab_ascii.setBounds(20,270,80,40);

        jpa_trans.add(jTextField_ascii);
        jpa_trans.add(jTextField_base64);
        jpa_trans.add(jTextField_src);
        jpa_trans.add(jTextField_unicode);
        jpa_trans.add(jTextField_url);
        jpa_trans.add(jTextField_utf);

        jpa_trans.add(lab_ascii);
        jpa_trans.add(lab_base64);
        jpa_trans.add(lab_unicode);
        jpa_trans.add(lab_url);
        jpa_trans.add(lab_utf);

        jpa_trans.add(jb_decode);
        jpa_trans.add(jb_encode);

        writer1.setBounds(350,310,250,40);
        email1.setBounds(350,330,250,40);

        jpa_trans.add(writer1);
        jpa_trans.add(email1);

        writer2.setBounds(350,310,250,40);
        email2.setBounds(350,330,250,40);
        jpa_system.add(writer2);
        jpa_system.add(email2);


        jb_decode.addActionListener(new myLisenter());
        jb_encode.addActionListener(new myLisenter());

        tp.setBounds(0,0,500,400);
        tp.add("进制转换",jpa_system);
        tp.add("编码转换",jpa_trans);
        ct.add(tp);

        jfrm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jfrm.setVisible(true);


    }
}
