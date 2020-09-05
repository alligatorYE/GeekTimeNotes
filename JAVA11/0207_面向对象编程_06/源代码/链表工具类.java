interface ILink<T> {	// ��������Ĺ���������׼
	public void add(T data) ; 
	public int size() ;
	public boolean isEmpty() ;
	public Object[] toArray() ; // ����תΪ��������
	public T get(int index) ;
	// ����ֵ��ԭʼ����
	public T set(int index, T newData) ;
	public boolean contains(T data) ;
	public void remove(T data) ;
	public void clear() ;
}
class LinkImpl<T> implements ILink<T> {
	private class Node<T> {
		private T data ; // ����
		private Node<T> next ;
		public Node(T data) {
			this.data = data ;
		}
		// ��1�ε��÷�����this = LinkImpl.root��
		// ��2�ε��÷�����this = LinkImpl.root.next��
		// ��3�ε��÷�����this = LinkImpl.root.next.next��
		public void addNode(Node<T> newNode) {	// ����Ǹ��ڵ�ڵ��ϵ
			if (this.next == null) {	// ��ǰ�ڵ�֮���п���
				this.next = newNode ; // ����ڵ�
			} else {
				this.next.addNode(newNode) ;
			}
		}
		public void toArrayNode() {
			LinkImpl.this.returnData[LinkImpl.this.foot ++] = this.data ; // ��ȡ��ǰ�ڵ�����
			if (this.next != null) {
				this.next.toArrayNode() ;// �ݹ����
			}
		}
		public T getNode(int index) {
			if (LinkImpl.this.foot ++ == index) {	// ������ͬ
				return this.data ; // ���ص�ǰ����
			} else {
				if (this.next != null) {
					return this.next.getNode(index) ;
				} else {	// ���һ����
					return null ;
				}
			}
		}
		public T setNode(int index,T newData) {
			if (LinkImpl.this.foot ++ == index) {	// �ҵ�������
				T temp = this.data ; // ��������
				this.data = newData ; // �޸�����
				return temp ; // ����ԭʼ����
			} else {
				if (this.next != null) {
					return this.next.setNode(index, newData) ;
				} else {
					return null ;
				}
			}
		}
		public boolean containsNode(T data) {
			if (this.data.equals(data)) {	// �����ж���ͬ
				return true ;
			} else {
				if (this.next != null) {
					return this.next.containsNode(data) ;
				} else {
					return false ;
				}
			}
		}
		public void removeNode(Node<T> previous, T data) {
			if (this.data.equals(data)) {
				previous.next = this.next ; // �ճ���ǰ�ڵ�
			} else {
				if (this.next != null) {	// ���к����ڵ�
					this.next.removeNode(this, data) ;
				}
			}
		}
	}
	private Node<T> root ; // ���ڵ�
	private int count ; // ͳ��Ԫ�ظ���
	private int foot ; // �����ű�
	private Object[] returnData ; // ��������
	// ============== ����Ϊ�ڲ��Ľڵ��ϵ�� ============= //
	public void add(T data) {	// ʵ����������
		if (data == null) {	// �ų������еĿ�Ԫ��
			return ;
		}
		Node<T> newNode = new Node<T>(data) ; // �����ݷ�װ�ڽڵ���
		if (this.root == null) {	// û�и��ڵ�
			this.root = newNode ; // ��һ���ڵ���Ϊ���ڵ�
		} else {	// ��ʱ���ڵ����
			this.root.addNode(newNode) ;	// ����Node�ദ��
		}
		this.count ++ ;
	}
	public int size() {
		return this.count ; // ���ظ���
	}
	public boolean isEmpty() {
		return this.size() == 0 ;
	}
	public Object[] toArray() {
		if (this.size() == 0) {	// û���κ�Ԫ�ر���
			return null ; 
		}
		this.foot = 0 ; // �ű�����
		this.returnData = new Object [this.size()] ;
		this.root.toArrayNode() ; // ����Node�ฺ����
		return this.returnData ; // ���ش�����
	}
	public T get(int index) {
		if (index >= this.size()) {
			return null ;
		}
		this.foot = 0 ;
		return this.root.getNode(index) ; // ����Node�����
	}
	public T set(int index, T newData) {
		if (index >= this.size()) {	// ������Χ
			return null ;
		}
		this.foot = 0 ;
		return this.root.setNode(index,newData) ;
	}
	public boolean contains(T data) {
		if (this.size() == 0 || data == null) {
			return false ;
		}
		return this.root.containsNode(data) ;
	}
	public void remove(T data) {
		if (this.size() == 0 || data == null) {
			return ;
		}
		if (this.root.data.equals(data)) {	// ���ڵ�
			this.root = this.root.next ; // �޸ĸ��ڵ�
		} else {	// �������жϿ��Դӵڸ��ڵ㿪ʼ
			if (this.root.next != null) {
				this.root.next.removeNode(this.root, data) ;
			}
		}
		this.count -- ;
	}
	public void clear() {
		this.root = null ; // �����������
		this.count = 0 ;
	}
}
public class TestDemo {	// ����
	public static void main(String args[]) {	// ������
		ILink<String> link = new LinkImpl<String>() ;
		link.add("Hello") ;
		link.add("World") ;
		link.add("MLDN") ;
		link.clear() ;
		System.out.println(link.get(0)) ;
	}
}