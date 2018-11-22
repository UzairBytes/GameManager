package Twenty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

import java.util.Observable;
import java.util.Observer;

import fall2018.csc2017.CoreClasses.GameActivity;
import fall2018.csc2017.CoreClasses.R;


public class TwentyGameActivity extends GameActivity {

    private TwentyBoardManager boardManager;

    private TwentyGestureDetectGridView gridView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadFromFile();
        setContentView(R.layout.activity_twenty_game);

        gridView = findViewById(R.id.gridTwenty);
        gridView.setNumColumns(boardManager.getSize());
        gridView.setBoardManager(boardManager);

    }

}
