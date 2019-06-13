package cm;
import java.util.Vector;

public class Basket {
	
	private Vector<Integer> productID; // 제품 아이디
	private Vector<Vector<Integer>> personalOrder; // 퍼스널 오더
	
	public Basket() {
		this.productID = new Vector<Integer>(2);
		this.personalOrder = new Vector<Vector<Integer>>(2);
	}
	
	public Vector<Integer> getProducts() {
		return this.productID;
	}
	
	public Vector<Vector<Integer>> getPO() {
		return this.personalOrder;
	}
	
	public void addBProduct(int productID) {
		this.productID.addElement(productID);
	}
	
	public void deleteBProduct(int productID) {
		for(int i = 0; i < this.productID.size(); i++) {
			if(this.productID.elementAt(i) == productID) {
				this.productID.removeElementAt(i);
				break;
			}
		}
	}
	
	public void addPO(int poID, int poState) {
		Vector<Integer> temp = new Vector<Integer>(2);
		temp.addElement(poID);
		temp.addElement(poState);
		this.personalOrder.addElement(temp);
	}
	
	public void deletePO(int poID) {
		for(int i = 0; i < this.personalOrder.size(); i++) {
			if(this.personalOrder.elementAt(i).elementAt(0) == poID) {
				this.personalOrder.removeElementAt(i);
				break;
			}
		}
	}
	
	public static Vector<String> getBasket(Basket basket) {
		// 인자 값인 basket 변수를 이용해 주문서 내용(output)을 구성하는 함수입니다.
		Vector<String> output = new Vector<String>(1);
		
		if(basket.productID.size() > 0) {
			output.add("== 주문서 == \n[메뉴]");
		}
		
		for(int i = 0; i < basket.productID.size(); i++) {
			int id = basket.productID.elementAt(i);
			if(id == 0) {
				output.add("- 버거 1");
			}else if(id == 1) {
				output.add("- 버거 2");
			}else if(id == 2) {
				output.add("- 버거 3");
			}else if(id == 3) {
				output.add("- 사이드 1");
			}else if(id == 4) {
				output.add("- 사이드 2");
			}
		}
		
		if(basket.personalOrder.size() > 0) {
			output.add("[퍼스널 오더]");
		}
		
		for(int i = 0; i < basket.personalOrder.size(); i++) {
			int id = basket.personalOrder.elementAt(i).elementAt(0);
			if(id == 5) {
				output.add("- 토핑 1 추가");
			}else if(id == 6) {
				output.add("- 토핑 2 추가");
			}else if(id == 7) {
				output.add("- 토핑 3 추가");
			}
		}
		
		return output;
	}
}
