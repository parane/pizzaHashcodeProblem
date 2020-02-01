import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static Integer maxCell;
    public static Integer minElement;
    public static Integer pizzaRow;
    public static Integer pizzaCol;

    public static void main(String[] args) throws IOException {

        Cell[][] cells= getCellInfo();
        //get the minmum cell rectangle that is minElement*2 to maxCell

        //2*2 horizontal slice Any valid Slice ??
        List<Slice> childPiece = getValidSlice(1,2,cells,null);

        //merge Cell To get optimum soln
        System.out.println("Hello World!"+cells[0][1]);
    }

    static List<Slice> getValidSlice(int row, int col,Cell[][] cells,List<Slice> alreadySlicePiece){


        int noOfT=0;
        int noOfM=0;

        List<Slice> validSlice= new ArrayList<>();
        for(int i=0;i<pizzaRow;i=i+row) {
            for (int j = 0; j < pizzaCol; j=j+col) {
                if(!isAnyOverlap(alreadySlicePiece,i,j,i+row,j+row)&& isValidSlice(i,j,row,col,cells)){
                    Slice slice=new Slice();
                    slice.startCell= cells[i][j];
                    slice.endCell=cells[i+row][j+col];
                    validSlice.add(slice);
                }

            }
        }

        return validSlice;

    }

    static boolean isAnyOverlap(List<Slice> slices,int startRow, int startCol, int endRow, int endCol){
        if(slices==null){
            return false;
        }
        for(Slice slice:slices){
            if(slice.startCell.row<=startRow&& slice.endCell.row>=startRow && slice.startCell.column<=startCol&& slice.endCell.column>=startCol){
                return true;
            }
            if(slice.startCell.row<=endRow&& slice.endCell.row>=endRow && slice.startCell.column<=endCol&& slice.endCell.column>=endCol){
                return true;
            }
        }

        return false;
    }


    static boolean isValidSlice(int startRow, int startCol, int row, int col,Cell[][] cells){
        int noOfT=0;
        int noOfM=0;

        for(int i=startRow;i<row;i++){
            for(int j=startCol;j<col;j++){

                if(cells[i][j].content.equalsIgnoreCase("T")){
                    noOfT++;
                }
                else{
                    noOfM++;
                }

                if(noOfM>=minElement&& noOfT>=minElement){
                    return true;
                }
            }
        }
        return false;

    }

    static Cell[][]  getCellInfo() throws IOException {
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
        String str;
        str = obj.readLine();
        String[] splited = str.split("\\s+");
        pizzaRow = Integer.parseInt(splited[0]);
        pizzaCol = Integer.parseInt(splited[1]);
        minElement = Integer.parseInt(splited[2]);
        maxCell = Integer.parseInt(splited[3]);

        Cell[][] cells= new Cell[pizzaRow][pizzaCol];
        int rowIndex=0;


        while (rowIndex< pizzaRow &&(str = obj.readLine()) != null) {

            for(int i=0;i<pizzaCol;i++){
                Cell cell= new Cell();
                cell.column = i;
                cell.row = rowIndex;
                cell.content= String.valueOf(str.charAt(i));
                cells[rowIndex][i] =cell;
            }

            rowIndex++;

        }

        return cells;
    }
}

