
public class AP {
	public static String[] title_name = { "Wifi����", "·����MAC��ַ","�ŵ�","���ܷ�ʽ","�ź�ǿ��","�Ƿ���ڹ���" };
	String name;
	String mac;
	String way;
	String secret_type;
	String scale;
	String attack;
	public AP(String name,String mac, String way, String secret_type, String scale, String attack) {
		super();
		this.name = name;
		this.mac = mac;
		this.way = way;
		this.secret_type = secret_type;
		this.scale = scale;
		this.attack = attack;
	}
	public Object[] getAP() {
		return new Object[]{name,mac,way,secret_type,scale,attack};
	}
	public static AP[] getDatas() {
		AP p = new AP("cmcc","FF:FC:D3:46:40", "6", "WPA2", "3", "����AP");
		AP p2 = new AP("cmcc-edu","FF:FC:o3:46:00", "7", "WPA1/WPA2", "1", "����AP");
		AP p3 = new AP("unicome","FF:FD:H3:46:44", "7", "WPA1", "0", "����");
		AP p4 = new AP("mianliao","FF:FC:e3:23:24", "7", "WPA2", "6", "����AP");
		AP p5 = new AP("cmcc2","FF:fC:D3:06:40", "7", "WPA1", "9", "����");
		return new AP[]{p,p2,p3,p4,p5};
	}
	
}
