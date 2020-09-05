interface ILink<T> {	// 建立链表的公共操作标准
	public void add(T data) ; 
	public int size() ;
	public boolean isEmpty() ;
	public Object[] toArray() ; // 数据转为对象数组
	public T get(int index) ;
	// 返回值：原始数据
	public T set(int index, T newData) ;
	public boolean contains(T data) ;
	public void remove(T data) ;
	public void clear() ;
}
class LinkImpl<T> implements ILink<T> {
	private class Node<T> {
		private T data ; // 数据
		private Node<T> next ;
		public Node(T data) {
			this.data = data ;
		}
		// 第1次调用方法：this = LinkImpl.root；
		// 第2次调用方法：this = LinkImpl.root.next；
		// 第3次调用方法：this = LinkImpl.root.next.next；
		public void addNode(Node<T> newNode) {	// 负责非根节点节点关系
			if (this.next == null) {	// 当前节点之后有空余
				this.next = newNode ; // 保存节点
			} else {
				this.next.addNode(newNode) ;
			}
		}
		public void toArrayNode() {
			LinkImpl.this.returnData[LinkImpl.this.foot ++] = this.data ; // 获取当前节点数据
			if (this.next != null) {
				this.next.toArrayNode() ;// 递归调用
			}
		}
		public T getNode(int index) {
			if (LinkImpl.this.foot ++ == index) {	// 索引相同
				return this.data ; // 返回当前数据
			} else {
				if (this.next != null) {
					return this.next.getNode(index) ;
				} else {	// 最后一个了
					return null ;
				}
			}
		}
		public T setNode(int index,T newData) {
			if (LinkImpl.this.foot ++ == index) {	// 找到索引了
				T temp = this.data ; // 返回数据
				this.data = newData ; // 修改数据
				return temp ; // 返回原始数据
			} else {
				if (this.next != null) {
					return this.next.setNode(index, newData) ;
				} else {
					return null ;
				}
			}
		}
		public boolean containsNode(T data) {
			if (this.data.equals(data)) {	// 数据判断相同
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
				previous.next = this.next ; // 空出当前节点
			} else {
				if (this.next != null) {	// 还有后续节点
					this.next.removeNode(this, data) ;
				}
			}
		}
	}
	private Node<T> root ; // 根节点
	private int count ; // 统计元素个数
	private int foot ; // 索引脚标
	private Object[] returnData ; // 返回数组
	// ============== 以上为内部的节点关系类 ============= //
	public void add(T data) {	// 实现数据增加
		if (data == null) {	// 排除掉所有的空元素
			return ;
		}
		Node<T> newNode = new Node<T>(data) ; // 将数据封装在节点中
		if (this.root == null) {	// 没有根节点
			this.root = newNode ; // 第一个节点作为根节点
		} else {	// 此时根节点存在
			this.root.addNode(newNode) ;	// 交由Node类处理
		}
		this.count ++ ;
	}
	public int size() {
		return this.count ; // 返回个数
	}
	public boolean isEmpty() {
		return this.size() == 0 ;
	}
	public Object[] toArray() {
		if (this.size() == 0) {	// 没有任何元素保存
			return null ; 
		}
		this.foot = 0 ; // 脚标清零
		this.returnData = new Object [this.size()] ;
		this.root.toArrayNode() ; // 交由Node类负责处理
		return this.returnData ; // 返回处理结果
	}
	public T get(int index) {
		if (index >= this.size()) {
			return null ;
		}
		this.foot = 0 ;
		return this.root.getNode(index) ; // 交由Node类完成
	}
	public T set(int index, T newData) {
		if (index >= this.size()) {	// 超过范围
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
		if (this.root.data.equals(data)) {	// 根节点
			this.root = this.root.next ; // 修改根节点
		} else {	// 后续的判断可以从第个节点开始
			if (this.root.next != null) {
				this.root.next.removeNode(this.root, data) ;
			}
		}
		this.count -- ;
	}
	public void clear() {
		this.root = null ; // 清空所有引用
		this.count = 0 ;
	}
}
public class TestDemo {	// 主类
	public static void main(String args[]) {	// 主方法
		ILink<String> link = new LinkImpl<String>() ;
		link.add("Hello") ;
		link.add("World") ;
		link.add("MLDN") ;
		link.clear() ;
		System.out.println(link.get(0)) ;
	}
}