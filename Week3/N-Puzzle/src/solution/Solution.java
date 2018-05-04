package solution;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;
import java.util.*;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

/**
 * 在此类中填充算法，完成重拼图游戏（N-数码问题）
 */
public class Solution extends Jigsaw {
	private final int maxSearchNum = 25000;
    private List<JigsawNode> solutionPath;// 解路径：用以保存从起始状态到达目标状态的移动路径中的每一个状态节点
    private int searchedNodesNum;			// 已访问节点数：用以记录所有访问过的节点的数量
    private HashSet<JigsawNode> visitedList;
    private LinkedList<JigsawNode> openList;

	/**
	 * 拼图构造函数
	 */
	public Solution() {
	}

	/**
	 * 拼图构造函数
	 * @param bNode - 初始状态节点
	 * @param eNode - 目标状态节点
	 */
	public Solution(JigsawNode bNode, JigsawNode eNode) {
		super(bNode, eNode);
	}

	
	/**
	 *（实验一）广度优先搜索算法，求指定5*5拼图（24-数码问题）的最优解
     * 填充此函数，可在Solution类中添加其他函数，属性
	 * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
	 * @return 搜索成功时为true,失败为false
	 */
	public boolean BFSearch(JigsawNode bNode, JigsawNode eNode){
		super.setBeginJNode(bNode);
		super.setEndJNode(eNode);

		openList = new LinkedList<JigsawNode>();
		visitedList = new HashSet<JigsawNode>();
		try{
			PrintWriter file = new PrintWriter(new File("BFSearchLog.txt"));
			openList.push(bNode);
			boolean isCompleted = false;
			while(!openList.isEmpty() && searchedNodesNum <= maxSearchNum){
				searchedNodesNum++;
				currentJNode = openList.poll();

				if(currentJNode.equals(eNode)){
					getPath();
					isCompleted = true;
					break;
				}

				JigsawNode[] nextNodes = new JigsawNode[]{
                	new JigsawNode(this.currentJNode), new JigsawNode(this.currentJNode),
                	new JigsawNode(this.currentJNode), new JigsawNode(this.currentJNode)
            	};

				for(int i=0;i<4;i++){
					if(nextNodes[i].move(i) && !visitedList.contains(nextNodes[i])){
						visitedList.add(nextNodes[i]);
                    	openList.add(nextNodes[i]);
					}
				}

			}
			printR(file);
			return isCompleted;
		}catch(IOException e){
			System.out.println("File Not Found....");
		}
		return false;
	}

	/**
	 *（Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)
	 * 如 f(n) = s(n). s(n)代表后续节点不正确的数码个数
     * 此函数会改变该节点的estimatedValue属性值
     * 修改此函数，可在Solution类中添加其他函数，属性
	 * @param jNode - 要计算代价估计值的节点
	 */
	public void estimateValue(JigsawNode jNode) {
		int f1 = 0; // All the numbers that are not in the correct locations
		int f2 = 0; // The distances between those in wrong places and the locations 
				 	// that they are supposed to be
		int f3 = 0; // The number of incorrect numbers in the later JigsawNodes
		int dimension = JigsawNode.getDimension();
		for (int index = 1; index < dimension * dimension; index++) {
			if (jNode.getNodesState()[index] + 1 != jNode.getNodesState()[index + 1]) {
				f3++;
			}
			if(index != jNode.getNodesState()[0]){
				if(jNode.getNodesState()[index] != index){
					f1++;
					int wrongLocX = (jNode.getNodesState()[index]-1) / dimension;
					int wrongLocY = (jNode.getNodesState()[index]-1) % dimension;
					int rightLocX = (index - 1) / dimension;
					int rightLocY = (index - 1) % dimension;
					int disX = Math.abs(wrongLocX - rightLocX);
					int disY = Math.abs(wrongLocY - rightLocY);
					f2 += (disX + disY);
				}
			}
		}
		int s = 2 * f1 + 6 * f2 + 2 * f3;
		jNode.setEstimatedValue(s);
	}

	public void printR(PrintWriter pw) throws IOException{
        boolean flag = false;
        if (pw == null) {
            pw = new PrintWriter(new FileWriter("Result.txt"));// 将搜索过程写入D://BFSearchDialog.txt
            flag = true;
        }
        if (isCompleted() == true) {
            // 写入文件
            pw.println("Jigsaw Completed");
            pw.println("Begin state:" + getBeginJNode().toString());
            pw.println("End state:" + getEndJNode().toString());
            pw.println("Solution Path: ");
            pw.println(getSolutionPath());
            pw.println("Total number of searched nodes:" + searchedNodesNum);
            pw.println("Length of the solution path is:" + getCurrentJNode().getNodeDepth());


            // 输出到控制台
            System.out.println("Jigsaw Completed");
            System.out.println("Begin state:" + getBeginJNode().toString());
            System.out.println("End state:" + getEndJNode().toString());
            System.out.println("Solution Path: ");
            System.out.println(getSolutionPath());
            System.out.println("Total number of searched nodes:" + searchedNodesNum);
            System.out.println("Length of the solution path is:" + getCurrentJNode().getNodeDepth());


        } else {
            // 写入文件
            pw.println("No solution. Jigsaw Not Completed");
            pw.println("Begin state:" + getBeginJNode().toString());
            pw.println("End state:" + getEndJNode().toString());
            pw.println("Total number of searched nodes:"
                    + searchedNodesNum);

            // 输出到控制台
            System.out.println("No solution. Jigsaw Not Completed");
            System.out.println("Begin state:" + getBeginJNode().toString());
            System.out.println("End state:" + getEndJNode().toString());
            System.out.println("Total number of searched nodes:"
                    + searchedNodesNum);
        }
        if (flag) {
            pw.close();
        }
    }
}
