public class ExcelSumFormula {
    /**
     * Your task is to design the basic function of Excel and implement the function
     * of sum formula. Specifically, you need to implement the following functions:
     * 
     * Excel(int H, char W): This is the constructor. The inputs represents the
     * height and width of the Excel form. H is a positive integer, range from 1 to
     * 26. It represents the height. W is a character range from 'A' to 'Z'. It
     * represents that the width is the number of characters from 'A' to W. The
     * Excel form content is represented by a height * width 2D integer array C, it
     * should be initialized to zero. You should assume that the first row of C
     * starts from 1, and the first column of C starts from 'A'.
     * 
     * 
     * void Set(int row, char column, int val): Change the value at C(row, column)
     * to be val.
     * 
     * 
     * int Get(int row, char column): Return the value at C(row, column).
     * 
     * 
     * int Sum(int row, char column, List of Strings : numbers): This function
     * calculate and set the value at C(row, column), where the value should be the
     * sum of cells represented by numbers. This function return the sum result at
     * C(row, column). This sum formula should exist until this cell is overlapped
     * by another value or another sum formula.
     * 
     * numbers is a list of strings that each string represent a cell or a range of
     * cells. If the string represent a single cell, then it has the following
     * format : ColRow. For example, "F7" represents the cell at (7, F).
     * 
     * If the string represent a range of cells, then it has the following format :
     * ColRow1:ColRow2. The range will always be a rectangle, and ColRow1 represent
     * the position of the top-left cell, and ColRow2 represents the position of the
     * bottom-right cell.
     * 
     * 
     * * 
     * Excel(3,"C"); 
    // construct a 3*3 2D array with all zero.
    //   A B C
    // 1 0 0 0
    // 2 0 0 0
    // 3 0 0 0

    Set(1, "A", 2);
    // set C(1,"A") to be 2.
    //   A B C
    // 1 2 0 0
    // 2 0 0 0
    // 3 0 0 0

    Sum(3, "C", ["A1", "A1:B2"]);
    // set C(3,"C") to be the sum of value at C(1,"A") and the values sum of the rectangle range whose top-left cell is C(1,"A") and bottom-right cell is C(2,"B"). Return 4. 
    //   A B C
    // 1 2 0 0
    // 2 0 0 0
    // 3 0 0 4

    Set(2, "B", 2);
    // set C(2,"B") to be 2. Note C(3, "C") should also be changed.
    //   A B C
    // 1 2 0 0
    // 2 0 2 0
    // 3 0 0 6
    */

    /**
     * you could assume that there won't be any circular sum reference. For example, A1 = sum(B1) and B1 = sum(A1).
     *
     * 
     * 
     * /** Approach #1 Using Topological Sort[Accepted] Before discussing the
     * required design, we'll discuss some prerequisites to help ease the
     * understanding of the solution.
     * 
     * Firstly, we can note that once a formula is applied to any cell in excel,
     * let's say C1 = C2 + C3C1=C2+C3, if any change is made to C2C2 or C3C3, the
     * result to be put into C1C1 needs to be evaluated again based on the new
     * values of C2C2 and C3C3. Further, suppose some other cell, say D2D2 is also
     * dependent on C1C1 due to some prior formula applied to D2D2. Then, when any
     * change is made to, say, C2C2, we re-evaluate C1C1's value. Furhter, since
     * D2D2 is dependent on C1C1, we need to re-evaluate D2D2's value as well.
     * 
     * Thus, whenever, we make any change to any cell, xx, we need to determine the
     * cells which are dependent on xx, and update these cells, and further
     * determine the cells which are dependent on the changed cells and so on. We
     * can assume that no cycles are present in the formulas, i.e. Any cell's value
     * won't directly or indirectly be dependent on its own value.
     * 
     * But, while doing these set of evaluations of the cells to determine their
     * updated values, we need to update the cells in such an order that the cell on
     * which some other cell is dependent is always evaluated prior to the cell
     * which is dependent on the former cell.
     * 
     * In order to do so, we can view the dependence between the cells in the form
     * of a dependency graph, which can be a Directed Graph. Since, no cycles are
     * allowed between the formulas, the graph reduces to a Directed Acyclic Graph.
     * Now, to solve the problem of evaluating the cells in the required order, we
     * can make use of a very well known method specifically used for such problems
     * in Directed Acyclic Graphs, known as the Topological Sorting.
     * 
     * Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of
     * vertices such that for every directed edge uvuv, vertex uu comes before vv in
     * the ordering. For example, a topological sorting of the following graph is 5
     * 4 2 3 1 0.
     * 
     * There can be more than one topological sorting for a graph. For example,
     * another topological sorting of the following graph is 4 5 2 3 1 0. The first
     * vertex in topological sorting is always a vertex with in-degree as 0 (a
     * vertex with no in-coming edges).
     * 
     * 
     * Topological Sorting can be done if we modify the Depth First Search to some
     * extent. In Depth First Search, we start from a vertex, we first print it and
     * then recursively call DFS for its adjacent vertices. Thus, the DFS obtained
     * for the graph above, starting from node 5, will be 5 2 3 1 0 4. But, in the
     * case of a topological sort, we can't print a node until all the nodes on
     * which it is dependent have already been printed.
     * 
     * To solve this problem, we make use of a temporary stack. We do the traversals
     * in the same manner as in DFS, but we don’t print the current node
     * immediately. Instead, for the current node we do as follows:
     * 
     * Recursively call topological sorting for all the nodes adjacent to the
     * current node.
     * 
     * Push the current node onto a stack.
     * 
     * Repeat the above process till all the nodes have been considered atleast
     * once.
     * 
     * Print the contents of the stack.
     * 
     * Note that a vertex is pushed to stack only when all of its
     * adjacent(dependent) vertices (and their adjacent(dependent) vertices and so
     * on) are already in stack. Thus, we obtain the correct ordering of the
     * vertices.
     * 
     * The following animation shows an example of topological sorting for the graph
     * above.
     * 
     * 
     * 
     * We can make use of the same concept while evaluating the cell values to
     * determine the order in which they need to be evaluated.
     * 
     * Now, let's discuss how we implement the various required functions. We make
     * use of a simple structure(Class), FormulaFormula, which contains two
     * elements. First, the value of the cell which it represents, valval, and a
     * HashMap, cellscells. It is a list of cells on which the current cell's value
     * is dependent. This cellscells hashmap stores the data in the form (cellName,
     * count)(cellName,count). cellNamecellName has the format ColRowColRow.
     * countcount refers to the number of times the current cell directly or
     * indirectly comes in the current cell's formulas. e.g. C1 = C2 + C3 +
     * C2C1=C2+C3+C2. In this case, the frequency of C3C3 is 1 and that of C2C2 is
     * 2.
     * 
     * Excel(int H, char W) : We simply need to initialize an array of
     * FormulaFormula with HH rows and the required number of columns corresponding
     * to WW.
     * 
     * set(int row, char column, int val) : For setting the value of the cell
     * corresponding to the given rowrow and columncolumn, we can simply change the
     * value , valval, in the FormulasFormulas array at the indices corresponding to
     * the current cell. Further, if any new formula is applied to a particular
     * cell, we need to remove the previously applied formulas on the same cell.
     * This is because two formulas can't be used to determine the value of a cell
     * simultaneously. Now, setting a cell to a particular value can also be seen as
     * a formula e.g. C1 = 2C1=2. Thus, we remove all the cellscells in the
     * FormulasFormulas for the current cell. Further, when the current cell's value
     * is changed, all the other cells which are dependent on it also need to be
     * evaluated in the correct order. Thus, we make use of Topological Sorting
     * starting with the current cell. We make use of a function topologicalSort(r,
     * c) for this purpose.
     * 
     * topologicalSort(r, c): In every call to this function, we traverse over all
     * the cells in the FormulasFormulas array and further apply topological sorting
     * to all the cells which are dependent on the current cell(row=r, column=c). To
     * find these cells, we can check the cellscells in the FormulasFormulas
     * associated with each cell and check if the current cell lies in it. After
     * applying Topological sorting to all these dependent cells, we put the current
     * cell onto a stackstack.
     * 
     * After doing the topological sorting, the cells on the stackstack lie in the
     * order in which their values should be evaluated given the current dependency
     * chain based on the formulas applied. Thus, we pick up these cells one by one,
     * and evaluate their values. To do the evaluation, we make use of
     * calculate_sum(r, c, cells). In this function, we traverse over all the
     * cellscells in the FormulasFormulas of the current cell(row=r, column=c), and
     * keep on adding their values. When this summing has been done, we update the
     * current cell's value, valval, to the sum just obtained. We keep on doing so
     * till all the cells in the stackstack have been evaluated.
     * 
     * get(int row, char column) : We can simply obtain the value(valval) associated
     * with the current cell's FormulasFormulas. If the cell has never been
     * initialized previously, we can return a 0 value.
     * 
     * sum(int row, char column, List of Strings : numbers) : To implement this
     * function, firstly, we need to expand the given numbersnumbers to obtain all
     * the cells which need to be added in the current formula. We obtain them, by
     * making use of a convert function, which extracts all these cells by doing
     * appropriate expansions based on : values. We put all these cells in the
     * cellscells associated with the current cell's FormulasFormulas. We also need
     * to set the current cell's value to a new value based on the current formula
     * added. For this, we make use of calculate_sum function as discussed above. We
     * also need to do the topological sorting and evaluate all the cells dependent
     * on the current cell. This is done in the same manner as in the set function
     * discussed above. We also need to return the value to which the current cell
     * has been set.
     */

    public class Excel {
        Formula[][] Formulas;

        class Formula {
            Formula(HashMap<String, Integer> c, int v) {
                val = v;
                cells = c;
            }

            HashMap<String, Integer> cells;
            int val;
        }

        Stack<int[]> stack = new Stack<>();

        public Excel(int H, char W) {
            Formulas = new Formula[H][(W - 'A') + 1];
        }

        public int get(int r, char c) {
            if (Formulas[r - 1][c - 'A'] == null)
                return 0;
            return Formulas[r - 1][c - 'A'].val;
        }

        public void set(int r, char c, int v) {
            Formulas[r - 1][c - 'A'] = new Formula(new HashMap<String, Integer>(), v);
            topologicalSort(r - 1, c - 'A');
            execute_stack();
        }

        public int sum(int r, char c, String[] strs) {
            HashMap<String, Integer> cells = convert(strs);
            int summ = calculate_sum(r - 1, c - 'A', cells);
            set(r, c, summ);
            Formulas[r - 1][c - 'A'] = new Formula(cells, summ);
            return summ;
        }

        public void topologicalSort(int r, int c) {
            for (int i = 0; i < Formulas.length; i++)
                for (int j = 0; j < Formulas[0].length; j++)
                    if (Formulas[i][j] != null && Formulas[i][j].cells.containsKey("" + (char) ('A' + c) + (r + 1))) {
                        topologicalSort(i, j);
                    }
            stack.push(new int[] { r, c });
        }

        public void execute_stack() {
            while (!stack.isEmpty()) {
                int[] top = stack.pop();
                if (Formulas[top[0]][top[1]].cells.size() > 0)
                    calculate_sum(top[0], top[1], Formulas[top[0]][top[1]].cells);
            }
        }

        public HashMap<String, Integer> convert(String[] strs) {
            HashMap<String, Integer> res = new HashMap<>();
            for (String st : strs) {
                if (st.indexOf(":") < 0)
                    res.put(st, res.getOrDefault(st, 0) + 1);
                else {
                    String[] cells = st.split(":");
                    int si = Integer.parseInt(cells[0].substring(1)), ei = Integer.parseInt(cells[1].substring(1));
                    char sj = cells[0].charAt(0), ej = cells[1].charAt(0);
                    for (int i = si; i <= ei; i++) {
                        for (char j = sj; j <= ej; j++) {
                            res.put("" + j + i, res.getOrDefault("" + j + i, 0) + 1);
                        }
                    }
                }
            }
            return res;
        }

        public int calculate_sum(int r, int c, HashMap<String, Integer> cells) {
            int sum = 0;
            for (String s : cells.keySet()) {
                int x = Integer.parseInt(s.substring(1)) - 1, y = s.charAt(0) - 'A';
                sum += (Formulas[x][y] != null ? Formulas[x][y].val : 0) * cells.get(s);
            }
            Formulas[r][c] = new Formula(cells, sum);
            return sum;
        }
    }

    /**
     * Your Excel object will be instantiated and called as such: Excel obj = new
     * Excel(H, W); obj.set(r,c,v); int param_2 = obj.get(r,c); int param_3 =
     * obj.sum(r,c,strs);
     */




    public class Excel_FromSolution {
        Cell[][] table;
    
        public Excel(int H, char W) {
            table = new Cell[H+1][W-'A'+1];
        }
        
        public void set(int r, char c, int v) {
            if(table[r][c-'A'] == null) table[r][c-'A'] = new Cell (v); 
            else table[r][c-'A'].setValue(v); 
        }
        
        public int get(int r, char c) {
            if( table[r][c-'A'] == null) return 0;
            else return table[r][c-'A'].getValue();  
        }
        
        public int sum(int r, char c, String[] strs) {
            if (table[r][c-'A'] == null) {
                table[r][c-'A'] = new Cell(strs);
            } else {
                table[r][c-'A'].setFormula(strs);
            }
            
            return table[r][c-'A'].getValue();
        }
        
        
        private class Cell{
            int val=0;
            HashMap<Cell, Integer> formula=new HashMap<>();//one cell might appear multiple times
            
            public Cell(int val){
                setValue(val); 
            }
            public Cell(String[] formulaStr){
                setFormula(formulaStr);
            }
            
            public void setValue(int val) {           
                formula.clear(); //will not be treated as a formula cell anymore        
                this.val = val;
            }
            
            public void setFormula(String[] formulaStr){
                formula.clear();            
                for(String str : formulaStr){
                    if (str.indexOf(":")<0){
                        int[] pos = getPos(str);
                        addFormulaCell(pos[0], pos[1]);
                    } else {
                        String[] pos = str.split(":");
                        int[] startPos = getPos(pos[0]);
                        int[] endPos = getPos(pos[1]);
                        for(int r = startPos[0]; r<=endPos[0]; r++){
                            for(int c = startPos[1]; c<=endPos[1]; c++){
                                addFormulaCell(r, c);
                            }
                        }
                    }
                }
            }
            
            private int[] getPos(String str){
                int[] pos = new int[2];
                pos[1]=str.charAt(0)-'A';
                pos[0]=Integer.parseInt(str.substring(1));
                return pos;
            }
            
            private void addFormulaCell(int r, int c){
                if(table[r][c] == null) table[r][c] = new Cell(0);
                Cell rangeCell = table[r][c];                            
                formula.put(rangeCell, (formula.containsKey(rangeCell)? formula.get(rangeCell) : 0)+1);
            }
            
            //recursive method
            private int getValue(){
                if(this.formula.isEmpty()) return this.val;
                int sum = 0;
                for(Cell cell : formula.keySet()){
                    sum+=cell.getValue()*formula.get(cell);
                }
                return sum;
            }
        }
    }
    

}