//プレイヤー数：2人
//石の総数：25個
//1度に取れる石の数：1～3個
import java.util.Scanner;
public class StoneGame {

    public static final int INIT = 0;
    public static final int MAIN = 1;
    public static final int EXIT = 2;

    public static final int SUMSTONE = 0;
    public static final int GETSTONE = 1;

    public static final int NOW = 0;
    public static final int OLD = 1;
    public static int gameState = INIT;

    public static boolean NameCheck(String[] playerName){
        for(var i = 0; i < playerName.length; i++){
            if(playerName[i].length() >= 2 && playerName[i].length() <= 26){;
                return false;
            }
        }
        return true;
    }

    public static int SumStone() {
        var sumstone = 0;

        do {
            sumstone = Int_InputText("石の総数を入力してください。(10 ~ 100)");
        } while (InputCheck(sumstone, 10, 100));

        return sumstone;
    }

    public static int GetStone(){

        var getstone =0;
        do {
            getstone = Int_InputText("取れる石の数を入力してください。");
        } while (InputCheck(getstone, 1, 10));

        return getstone;
    }

    //残り個数を表示する場所
    public static void LeftoverShowStones(boolean[] stones,int index ,String shape){
        System.out.println("残り : "+(index + 1)+"個");
        for(var i= 0; i <= index; i++){
            if(stones[i]) {
                System.out.print(" "+shape+" ");
            }
        }
        System.out.println("");
    }

    public static int GameMain(boolean[] stones,int [] stonesData ,String shape,int index,String [] player,int[] turn){
        //ゲーム開始
        //System.out.println("ループ開始位置");
        System.out.println("------------------------------");
        System.out.println(player[turn[0]]+"の番");

        //ループ処理
        var stoneNum = 0;
        //入力受付。

        do {
            stoneNum = Int_InputText("石の数を入力してください。(1~"+stonesData[GETSTONE]+"まで入力可能です。)");
        }while(InputCheck(stoneNum,1,stonesData[GETSTONE]));


        for (int i = 0; i < stoneNum; i++) {
            //取れるか確認
            if(index > 0) {//取る石が要素数以下なら終了
                if (stones[index]) {
                    stones[index] = false;
                    index--;
                    //ターン切り替え
                }
            }else{
                //前回のプレイヤーが勝ち
                turn[OLD] = turn[NOW];
                turn[OLD]--;
                if(turn[OLD] <= 0){
                    turn[OLD] = player.length - 1;
                }

                gameState = EXIT;
                System.out.println("勝者 : "+ player[turn[OLD]]);
                System.out.println("敗者 : "+ player[turn[NOW]]);
                return 0;
            }
        }

        //ターン交代
        turn[NOW]  = (++turn[NOW]) % player.length;

        //残り石表示
        LeftoverShowStones(stones,index,shape);

        return index;
    }

    public static boolean InputCheck(int num,int min ,int max){
        while (true){
            if(num >= min && num <= max){
                return false;
            }else{
                System.out.println((min+"~"+max+"の間で入力してください。"));
                return true;
            }
        }
    }

    public static int Int_InputText(String text){
        System.out.println(text);
        var sc = new Scanner(System.in);
        var str = sc.nextLine();
        return Integer.parseInt(str);
    }

    public static String InputText(){
        var sc = new Scanner(System.in);
        var str = sc.nextLine();
        return str;
    }
}
