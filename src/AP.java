
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
		AP p = new AP("cmcc","FF:FC:D3:46:40", "6", "WPA2", "||||", "����AP");
		AP p2 = new AP("cmcc2","FF:FC:D3:46:40", "7", "WPA1", "|||", "����AP");
		return new AP[]{p,p2};
	}
	
}
