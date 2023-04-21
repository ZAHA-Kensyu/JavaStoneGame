import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        boolean isGame = true;
        int[] stoneDate = new int[2];

        System.out.println("------------------------------");
        System.out.println("石取りゲームを開始します。");

        //石の総数
        stoneDate[StoneGame.SUMSTONE] = StoneGame.SumStone();

        //１度に取れる石の数。
        stoneDate[StoneGame.GETSTONE] = StoneGame.GetStone();

        //石を生成
        boolean[] stones = new boolean[stoneDate[StoneGame.SUMSTONE]];
        var index = stones.length - 1; //要素数

        String [] playerName;
        //2人以上か確認する
        //一人の長さが2=26かチェックする
        //一人でも長かったら再度入力させる。
        do {
            System.out.println("プレイヤー登録を行います。 (2~26文字以上),(カンマ)区切りで入力してください。 2名以上でゲームが始まります。");
            playerName = StoneGame.InputText().split(",");
        }while (StoneGame.NameCheck( playerName) || playerName.length < 2);


        //石の形を入力させる。
        System.out.println("石の形を入力してください。");
        var shape = StoneGame.InputText();

        System.out.println("------------------------------");

        //初期化処理
        for(var i= 0; i<stones.length; i++){
            stones[i] = true;
        }

        //残り確認
        StoneGame.LeftoverShowStones(stones,index,shape);

        //ゲームメインへ移動
        StoneGame.gameState = StoneGame.MAIN;

        //ターン
        int [] turn = new int[2];

        //ゲーム状態
        while(isGame) {
            switch (StoneGame.gameState) {
                case StoneGame.MAIN -> index = StoneGame.GameMain(stones,stoneDate,shape,index,playerName,turn);//ゲームメイン
                default -> isGame = false;//ゲーム終了
            }
        }
    }
}