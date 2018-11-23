public class ExcelFormula {

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
     */



    /**
     * Approach
     */


    /**
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

    Cell[][] excel;

    public Excel(int H, char W) {
        excel = new Cell[H][(W-'A')+1];
    }

    public void set(int r, char c, int v) {

        // Lets first the cell new value
        Cell cell = new Cell();
        cell.val = v;
        excel[r - 1][c - 'A'] = cell;

        // Lets update its dependencies
        // First get its dependencies using topological sort
        LinkedList<Cell> stack = new LinkedList<>();
        topologicalSort(r - 1, c - 'A', stack);

        // Then trigger execute for all the collected dependencies
        executeStack(stack);
    }

    public int get(int r, char c) {
        // straight forward
        if (excel[r - 1][c - 'A'] == null)
            return 0;
        return excel[r - 1][c - 'A'].val;
    }

    public int sum(int r, char c, String[] strs) {
        // First, create a formula
        HashMap<String, Integer> formula = convert(strs);

        // Then calculate the sum based on the formula
        int sum = calculateSum(r - 1, c - 'A', formula);

        // Then set it, also take care if dependencies are there
        set(r, c, sum);

        // finally update the value in the excel
        // This was done in previous step but only partialy, since we need to set
        // formula also
        excel[r - 1][c - 'A'] = new Cell(sum, formula);
        return sum;

    }

    private class Cell {
        int val;
        Map<String, Integer> formula; // String is for the cell and Integer is for the number of times, that cell is
                                      // involved int this formula

        int x;
        int y; // positions to sometimes used as DTO like in the stack

        public Cell() {
            formula = new HashMap<>();
        }

        public Cell(int x, int y) {
            formula = new HashMap<>();
            this.x = x;
            this.y = y;
        }

        public Cell(int val, Map<String, Integer> formula) {
            this.formula = formula;
            this.val = val;
        }
    }

    private void topologicalSort(int r, int c, LinkedList<Cell> stack) {
        for (int i = 0; i < excel.length; i++)
            for (int j = 0; j < excel[0].length; j++)
                if (excel[i][j] != null && excel[i][j].formula.containsKey("" + (char) ('A' + c) + (r + 1))) {
                    topologicalSort(i, j, stack);
                }

        stack.addFirst(new Cell(r, c));
    }

    public void executeStack(LinkedList<Cell> stack) {
        while (!stack.isEmpty()) {
            Cell top = stack.pollFirst();
            if (excel[top.x][top.y].formula.size() > 0)
                calculateSum(top.x, top.y, excel[top.x][top.y].formula);
        }
    }

    public int calculateSum(int r, int c, Map<String, Integer> formula) {
        int sum = 0;
        for (String s : formula.keySet()) {
            int x = Integer.parseInt(s.substring(1)) - 1, y = s.charAt(0) - 'A';
            sum += (excel[x][y] != null ? excel[x][y].val : 0) * formula.get(s);
        }
        excel[r][c] = new Cell(sum, formula);
        return sum;
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
}