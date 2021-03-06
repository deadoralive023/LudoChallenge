package com.example.apple.ludochallenge.networking;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.apple.ludochallenge.Color;
import com.example.apple.ludochallenge.LudoActivity;
import com.example.apple.ludochallenge.PlayerType;
import com.example.apple.ludochallenge.R;
import com.example.apple.ludochallenge.UserProgressData;
import com.example.apple.ludochallenge.WaitingForOpponent2Players;
import com.example.apple.ludochallenge.WaitingForOpponent4Players;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class PlayActivity extends AppCompatActivity {

    int noOfPlayers;
    int color = 1, vsComputer = 0;
    private ImageView logo;
    private ImageView diceGif0;
    private ImageView diceGif1;
    private ImageView diceGif2;
    private Button local_multiplayer;
    private FrameLayout frameLayout;
    private FrameLayout four_player_frameLayout;
    private FrameLayout three_player_frameLayout;
    private FrameLayout two_player_frameLayout;
    private ImageView selectNoOfPlayers_backBtn;
    private LinearLayout two_players, three_players, four_players;
    ImageView four_players_backBtn, four_players_playBtn;
    EditText four_players_player1_name, four_players_player2_name, four_players_player3_name, four_players_player4_name;
    ImageView three_players_backBtn, two_players_backBtn;
    EditText two_players_player1_name, two_players_player2_name;
    EditText three_players_player1_name, three_players_player2_name, three_players_player3_name;
    ImageView two_players_playBtn, three_players_playBtn;
    ImageView two_players_leftPress1, two_players_rightPress1, two_players_leftPress2, two_players_rightPress2;
    ImageView three_players_leftPress1, three_players_rightPress1, three_players_leftPress2, three_players_rightPress2, three_players_leftPress3, three_players_rightPress3;
    ImageView two_players_pawn1, two_players_pawn2, three_players_pawn1, three_players_pawn2, three_players_pawn3;
    int checkPawn = 0, checkPawn_3players = 0;
    int color_3players = 2;
    ImageView vsComputer_check1, vsComputer_check2, vsComputer_check3, vsComputer_check4;
    Button vsComputer_btn;
    FrameLayout vsComputer_frameLayout;
    ImageView vsComputer_playBtn, vsComputer_backBtn;
    int vsComputer_color = 1;
    ImageView edit_profile_dilaogBox_backBtn;
    LinearLayout profile_picBox;
    FrameLayout edit_profile_dialogBox;
    private ImageView flag_image, profile_image;
    private TextView userName, countryName;
    private DatabaseReference mUserDatabase;
    private StorageReference mImageStorage;
    private FirebaseUser mCurrentUser;
    private ImageView editProfile_userImage;
    private ImageView edit_profile_dialog_flagImage;
    private TextView edit_profile_dialog_userName;
    private TextView edit_profile_dialog_countryName;
    private ImageView edit_profile_dialog_coin;
    private TextView edit_profile_dialog_coinText;
    private Button playWithFriends;
    FrameLayout playWithFriends_dialogBox;
    ImageView playWithFriends_backBtn;
    private ImageView play_editProfileDialog_edit_profileBtn;
    ImageView playWithFriends_2players;
    public static MySQLDatabase mySQLDatabase;
    private String LOGIN_STATUS;
    String facebook_display_name;
    String facebook_country_name;
    byte[] facebook_country_image;
    byte[] facebook_photo_byte_array;
    private TextView play_dialog_coins;
    private TextView play_coins;
    private TextView play_dialog_LudoChallenge_vsComputer_win;
    private TextView play_dialog_LudoChallenge_vsComputer_lose;
    private TextView play_dialog_LudoChallenge_vsMultiplayer_win;
    private TextView play_dialog_LudoChallenge_vsMultiplayer_lose;
    private TextView play_dialog_SAL_vsMultiplayer_lose;
    private TextView play_dialog_SAL_vsMultiplayer_win;
    private TextView play_dialog_SAL_vsComputer_lose;
    private TextView play_dialog_SAL_vsComputer_win;
    private TextView play_dialog_level;
    private InterstitialAd interstitialAd;
    private String current_uid;
    private ImageView play_backBtn;
    private ImageView play_coinIcon;
    private FrameLayout onlineMultiplayer_dialog;
    private LinearLayout online_multiplayer_two_players;
    private LinearLayout online_multiplayer_four_players;
    private ImageView online_multiplyer_dialog_back;
    private Button online_multiplayer_btn;
    private ImageView three_players_playWithFriends;
    private ImageView four_players_playWithFriends;
    public static final String NAMES_KEY = "NAMES";
    public static final String PLAYERS_KEY = "PLAYERS";
    public static final String COLORS_KEY = "COLORS";
    public static final String PLAYERS_TYPE_KEY = "PLAYERS_TYPE";
    int[] colors_array = new int[]{Color.getInt(Color.RED), Color.getInt(Color.YELLOW)};
    private FrameLayout online_multiplayer_coins_2players_dialog;
    private ImageView multiplayer_coins_2players_btn1;
    private ImageView multiplayer_coins_2players_btn2;
    private ImageView multiplayer_coins_2players_btn3;
    private ImageView multiplayer_coins_2players_btn4;
    private ImageView multiplayer_coins_2players_btn5;
    private ImageView multiplayer_coins_2players_btn6;
    private ImageView multiplayer_coins_2players_btn7;
    private ImageView multiplayer_coins_2players_btn8;
    private ImageView multiplayer_coins_2players_btn9;
    private FrameLayout online_multiplayer_not_enough_coins_dialog;
    private ImageView online_multiplayer_play_another_room;
    private ImageView online_multiplayer_get_more_coins;
    private boolean checkOnlineCoinPlayers = false;
    private ImageView online_multiplayer_2Players_back;
    String facebook_uid;
    private FrameLayout online_multiplayer_coins_4players_dialog;
    private ImageView online_multiplayer_4Players_back;
    private ImageView online_multiplayer_coins_4players_btn1;
    private ImageView online_multiplayer_coins_4players_btn2;
    private ImageView online_multiplayer_coins_4players_btn3;
    private ImageView online_multiplayer_coins_4players_btn4;
    private ImageView online_multiplayer_coins_4players_btn5;
    private ImageView online_multiplayer_coins_4players_btn6;
    private ImageView online_multiplayer_coins_4players_btn7;
    private ImageView online_multiplayer_coins_4players_btn8;
    private ImageView online_multiplayer_coins_4players_btn9;
    private boolean check_3player_localClick  = false;
    private boolean check_2player_computerClick  = false;


    String LudoChallenge_vsComputer_WIN;
    String LudoChallenge_vsComputer_LOSE;
    String LudoChallenge_vsMultiplayer_WIN;
    String LudoChallenge_vsMultiplayer_LOSE;
    String SAL_vsComputer_WIN;
    String SAL_vsComputer_LOSE;
    String SAL_vsMultiplayer_WIN;
    String SAL_vsMultiplayer_LOSE;
    String coins;

    String[] names;
    Color[] colors;
    PlayerType[] playerType;
    int numberOfPlayers;
    private AnimationDrawable logoDrawable;
    private int hidden_clicked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

//        FirebaseAuth.getInstance().signOut();

        mImageStorage = FirebaseStorage.getInstance().getReference();
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();


        mySQLDatabase = MySQLDatabase.getInstance(this);
        LOGIN_STATUS = mySQLDatabase.fetchCurrentLoggedInStatus();
        flag_image = (ImageView) findViewById(R.id.play_flag_pic);
        profile_image = (ImageView) findViewById(R.id.play_profile_pic);
        userName = (TextView) findViewById(R.id.play_username);
        countryName = (TextView) findViewById(R.id.play_country_name);
        logo = (ImageView) findViewById(R.id.register_logo);
        diceGif0 = (ImageView) findViewById(R.id.play_selectNoOfPlayers_dilaog_gif0);
        diceGif1 = (ImageView) findViewById(R.id.play_selectNoOfPlayers_dilaog_gif1);
        diceGif2 = (ImageView) findViewById(R.id.play_selectNoOfPlayers_dilaog_gif2);
        Glide.with(getApplicationContext()).load(R.raw.logo).apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE)).into(logo);

//        logo.setImageResource(R.drawable.logo_anim);
//        logoDrawable = (AnimationDrawable) logo.getDrawable();
//        logoDrawable.start();

        Glide.with(getApplicationContext()).load(R.raw.dice_gif0).apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE)).into(diceGif0);
        Glide.with(getApplicationContext()).load(R.raw.dice_gif1).apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE)).into(diceGif1);
        Glide.with(getApplicationContext()).load(R.raw.dice_gif0).apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE)).into(diceGif2);

        two_players = (LinearLayout) findViewById(R.id.two_players);
        three_players = (LinearLayout) findViewById(R.id.three_players);
        two_players_backBtn = (ImageView) findViewById(R.id.play_two_players_back);
        two_players_player1_name = (EditText) findViewById(R.id.two_players_playerOne_name);
        two_players_player2_name = (EditText) findViewById(R.id.two_players_playerTwo_name);
        two_players_playBtn = (ImageView) findViewById(R.id.play_two_players_play);
        two_players_pawn1 = (ImageView) findViewById(R.id.two_players_pawn1);
        two_players_pawn2 = (ImageView) findViewById(R.id.two_players_pawn2);
        three_players_leftPress1 = (ImageView) findViewById(R.id.play_3players_leftBtn1);
        three_players_leftPress2 = (ImageView) findViewById(R.id.play_3players_leftBtn2);
        three_players_leftPress3 = (ImageView) findViewById(R.id.play_3players_leftBtn3);
        three_players_rightPress1 = (ImageView) findViewById(R.id.play_3players_rightBtn1);
        three_players_rightPress2 = (ImageView) findViewById(R.id.play_3players_rightBtn2);
        three_players_rightPress3 = (ImageView) findViewById(R.id.play_3players_rightBtn3);
        three_players_pawn1 = (ImageView) findViewById(R.id.three_players_pawn1);
        three_players_pawn2 = (ImageView) findViewById(R.id.three_players_pawn2);
        three_players_pawn3 = (ImageView) findViewById(R.id.three_players_pawn3);
        three_players_playBtn = (ImageView) findViewById(R.id.play_three_players_play);
        three_players_backBtn = (ImageView) findViewById(R.id.play_three_players_back);
        three_players_player1_name = (EditText) findViewById(R.id.three_players_playerOne_name);
        three_players_player2_name = (EditText) findViewById(R.id.three_players_playerTwo_name);
        three_players_player3_name = (EditText) findViewById(R.id.three_players_playerThree_name);
        vsComputer_check1 = (ImageView) findViewById(R.id.vs_computer_check1);
        vsComputer_check2 = (ImageView) findViewById(R.id.vs_computer_check2);
        vsComputer_check3 = (ImageView) findViewById(R.id.vs_computer_check3);
        vsComputer_check4 = (ImageView) findViewById(R.id.vs_computer_check4);
        vsComputer_btn = (Button) findViewById(R.id.vs_computer_btn);
        vsComputer_playBtn = (ImageView) findViewById(R.id.play_vsComputer_play);
        vsComputer_backBtn = (ImageView) findViewById(R.id.play_vsComputer_back);
        profile_picBox = (LinearLayout) findViewById(R.id.profile_pic_box);
        edit_profile_dialogBox = (FrameLayout) findViewById(R.id.edit_profile_dialogBox);
        edit_profile_dilaogBox_backBtn = (ImageView) findViewById(R.id.edit_profile_dilaogBox_backBtn);
        editProfile_userImage = (ImageView) findViewById(R.id.play_editProfile_userImage);
        edit_profile_dialog_flagImage = (ImageView) findViewById(R.id.edit_profile_dialog_flagImage);
        edit_profile_dialog_userName = (TextView) findViewById(R.id.edit_profile_dialog_userName);
        edit_profile_dialog_countryName = (TextView) findViewById(R.id.edit_profile_dialog_countryName);
        edit_profile_dialog_coin = (ImageView) findViewById(R.id.edit_profile_dialog_coin);
        edit_profile_dialog_coinText = (TextView) findViewById(R.id.play_dialog_coins);
        local_multiplayer = (Button) findViewById(R.id.play_local_multiplayer);
        frameLayout = (FrameLayout) findViewById(R.id.play_slectNoOfPlayers_dialogBox);
        selectNoOfPlayers_backBtn = (ImageView) findViewById(R.id.select_no_of_players_back);
        four_players = (LinearLayout) findViewById(R.id.four_players);
        four_player_frameLayout = (FrameLayout) findViewById(R.id.four_player_select_color);
        three_player_frameLayout =(FrameLayout) findViewById(R.id.three_player_select_color_3players);
        two_player_frameLayout =(FrameLayout) findViewById(R.id.two_player_select_color_2players);
        four_players_backBtn = (ImageView) findViewById(R.id.four_players_back);
        four_players_playBtn = (ImageView) findViewById(R.id.four_players_play);
        four_players_player1_name = (EditText) findViewById(R.id.four_players_playerOne_name);
        four_players_player2_name = (EditText) findViewById(R.id.four_players_playerTwo_name);
        four_players_player3_name = (EditText) findViewById(R.id.four_players_playerThree_name);
        four_players_player4_name = (EditText) findViewById(R.id.four_players_playerFour_name);
        two_players_leftPress1 = (ImageView) findViewById(R.id.play_2players_leftBtn1);
        two_players_leftPress2 = (ImageView) findViewById(R.id.play_2players_leftBtn2);
        two_players_rightPress1 = (ImageView) findViewById(R.id.play_2players_rightBtn1);
        two_players_rightPress2 = (ImageView) findViewById(R.id.play_2players_rightBtn2);
        vsComputer_frameLayout = (FrameLayout) findViewById(R.id.vs_computer_frameLayout);
        playWithFriends = (Button) findViewById(R.id.play_playWithFriends);
        playWithFriends_dialogBox = (FrameLayout) findViewById(R.id.play_slectNoOfPlayers_dialogBox_playWithFriends);
        playWithFriends_backBtn = (ImageView) findViewById(R.id.select_no_of_players_playWithFriends_back);
        playWithFriends_2players = (ImageView) findViewById(R.id.two_players_playWithFriends);
        play_editProfileDialog_edit_profileBtn = (ImageView) findViewById(R.id.play_editProfileDialog_edit_profileBtn);
        play_dialog_coins = (TextView) findViewById(R.id.play_dialog_coins);
        play_coins = (TextView) findViewById(R.id.play_coins);
        play_dialog_LudoChallenge_vsComputer_win = (TextView) findViewById(R.id.play_dialog_LudoChallenge_vsComputer_win);
        play_dialog_LudoChallenge_vsComputer_lose = (TextView) findViewById(R.id.play_dialog_LudoChallenge_vsComputer_lose);
        play_dialog_LudoChallenge_vsMultiplayer_win = (TextView) findViewById(R.id.play_dialog_LudoChallenge_vsMultiplayer_win);
        play_dialog_LudoChallenge_vsMultiplayer_lose = (TextView) findViewById(R.id.play_dialog_LudoChallenge_vsMultiplayer_lose);
        play_dialog_SAL_vsMultiplayer_lose = (TextView) findViewById(R.id.play_dialog_SAL_vsMultiplayer_lose);
        play_dialog_SAL_vsMultiplayer_win = (TextView) findViewById(R.id.play_dialog_SAL_vsMultiplayer_win);
        play_dialog_SAL_vsComputer_lose = (TextView) findViewById(R.id.play_dialog_SAL_vsComputer_lose);
        play_dialog_SAL_vsComputer_win = (TextView) findViewById(R.id.play_dialog_SAL_vsComputer_win);
        play_dialog_level = (TextView) findViewById(R.id.play_dialog_level);
        play_backBtn = (ImageView) findViewById(R.id.play_backBtn);
        play_coinIcon = (ImageView) findViewById(R.id.play_coinIcon);
        onlineMultiplayer_dialog = (FrameLayout) findViewById(R.id.play_slectNoOfPlayers_online_multiplayer_dialogBox);
        online_multiplayer_two_players = (LinearLayout) findViewById(R.id.online_multiplayer_two_players);
        online_multiplayer_four_players = (LinearLayout) findViewById(R.id.online_multiplayer_four_players);
        online_multiplyer_dialog_back = (ImageView) findViewById(R.id.online_multiplyer_dialog_back);
        online_multiplayer_btn = (Button) findViewById(R.id.online_multiplayer_btn);
        three_players_playWithFriends = (ImageView) findViewById(R.id.three_players_playWithFriends);
        four_players_playWithFriends = (ImageView) findViewById(R.id.four_players_playWithFriends);
        online_multiplayer_coins_2players_dialog = (FrameLayout) findViewById(R.id.online_multiplayer_coins_2players_dialog);
        multiplayer_coins_2players_btn1 = (ImageView) findViewById(R.id.online_multiplayer_coins_2players_btn1);
        multiplayer_coins_2players_btn2 = (ImageView) findViewById(R.id.online_multiplayer_coins_2players_btn2);
        multiplayer_coins_2players_btn3 = (ImageView) findViewById(R.id.online_multiplayer_coins_2players_btn3);
        multiplayer_coins_2players_btn4 = (ImageView) findViewById(R.id.online_multiplayer_coins_2players_btn4);
        multiplayer_coins_2players_btn5 = (ImageView) findViewById(R.id.online_multiplayer_coins_2players_btn5);
        multiplayer_coins_2players_btn6 = (ImageView) findViewById(R.id.online_multiplayer_coins_2players_btn6);
        multiplayer_coins_2players_btn7 = (ImageView) findViewById(R.id.online_multiplayer_coins_2players_btn7);
        multiplayer_coins_2players_btn8 = (ImageView) findViewById(R.id.online_multiplayer_coins_2players_btn8);
        multiplayer_coins_2players_btn9 = (ImageView) findViewById(R.id.online_multiplayer_coins_2players_btn9);
        online_multiplayer_not_enough_coins_dialog = (FrameLayout) findViewById(R.id.online_multiplayer_not_enough_coins_dialog);
        online_multiplayer_play_another_room = (ImageView) findViewById(R.id.online_multiplayer_play_another_room);
        online_multiplayer_get_more_coins = (ImageView)  findViewById(R.id.online_multiplayer_get_more_coins);
        online_multiplayer_2Players_back = (ImageView) findViewById(R.id.online_multiplayer_2Players_back);
        online_multiplayer_coins_4players_dialog = (FrameLayout) findViewById(R.id.online_multiplayer_coins_4players_dialog);
        online_multiplayer_4Players_back = (ImageView) findViewById(R.id.online_multiplayer_4Players_back);
        online_multiplayer_coins_4players_dialog = (FrameLayout) findViewById(R.id.online_multiplayer_coins_4players_dialog);
        online_multiplayer_4Players_back = (ImageView) findViewById(R.id.online_multiplayer_4Players_back);
        online_multiplayer_coins_4players_btn1 = (ImageView) findViewById(R.id.online_multiplayer_coins_4players_btn1);
        online_multiplayer_coins_4players_btn2 = (ImageView) findViewById(R.id.online_multiplayer_coins_4players_btn2);
        online_multiplayer_coins_4players_btn3 = (ImageView) findViewById(R.id.online_multiplayer_coins_4players_btn3);
        online_multiplayer_coins_4players_btn4 = (ImageView) findViewById(R.id.online_multiplayer_coins_4players_btn4);
        online_multiplayer_coins_4players_btn5 = (ImageView) findViewById(R.id.online_multiplayer_coins_4players_btn5);
        online_multiplayer_coins_4players_btn6 = (ImageView) findViewById(R.id.online_multiplayer_coins_4players_btn6);
        online_multiplayer_coins_4players_btn7 = (ImageView) findViewById(R.id.online_multiplayer_coins_4players_btn7);
        online_multiplayer_coins_4players_btn8 = (ImageView) findViewById(R.id.online_multiplayer_coins_4players_btn8);
        online_multiplayer_coins_4players_btn9 = (ImageView) findViewById(R.id.online_multiplayer_coins_4players_btn9);


        final Activity activity = this;
        View view = findViewById(R.id.gift_gap);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hidden_clicked++;
                if(hidden_clicked == 20) {
                    hidden_clicked = 0;
                    final LinearLayout gift_gif = findViewById(R.id.gift_gif);

                    gift_gif.setVisibility(View.VISIBLE);
                    ((AnimationDrawable)((ImageView)findViewById(R.id.gift_gif_image)).getDrawable()).start();


                    final TextView textView1 = findViewById(R.id.gift_text1);
                    final TextView textView2 = findViewById(R.id.gift_text2);

//                    TextSwitcher switcher = new TextSwitcher(getApplicationContext());
//
//                    switcher.addView(textView1);
//                    switcher.addView(textView2);

                    final Timer timer = new Timer();
                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    if(textView1.getCurrentTextColor() == android.graphics.Color.BLACK) {
                                        textView1.setTextColor(android.graphics.Color.YELLOW);
                                        textView2.setTextColor(android.graphics.Color.YELLOW);
                                    }
                                    else {
                                        textView1.setTextColor(android.graphics.Color.BLACK);
                                        textView2.setTextColor(android.graphics.Color.BLACK);
                                    }
                                }
                            });
//                            textView1.setText("A Gift From");
//                            textView2.setText("Xaara Meher");
                        }
                    }, 0, 100);


                    ObjectAnimator scaleX = ObjectAnimator.ofFloat(gift_gif,"scaleX",1f);
                    scaleX.setDuration(2000);
                    ObjectAnimator scaleY = ObjectAnimator.ofFloat(gift_gif,"scaleY",1f);
                    scaleY.setDuration(2000);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(scaleX,scaleY);
                    animatorSet.setInterpolator(new BounceInterpolator());
                    animatorSet.start();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            timer.cancel();
                            ((AnimationDrawable)((ImageView)findViewById(R.id.gift_gif_image)).getDrawable()).stop();
                            gift_gif.setVisibility(View.GONE);
                            gift_gif.setScaleX(0);
                            gift_gif.setScaleY(0);
                        }
                    },5000);
                }
            }
        });




        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                interstitialAd.loadAd(new AdRequest.Builder().build());
                if(LOGIN_STATUS.equals(MySQLDatabase.LOGIN_STATUS_FACEBOOK)){
                    Intent intent = new Intent(getApplicationContext(), UsersActivity.class);
                    intent.putExtra("noOfPlayers",noOfPlayers);
                    intent.putExtra("color",color);
                    intent.putExtra("vsComputer",vsComputer);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }else {
                    playWithFriends_dialogBox.setVisibility(View.VISIBLE);
                }
            }
        });



        if(!LOGIN_STATUS.equals(MySQLDatabase.LOGIN_STATUS_PLAY_AS_GUEST)) {
            current_uid = mCurrentUser.getUid();
            mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(current_uid);

            String ID = mySQLDatabase.fetchCurrentLoggedInID();
            ArrayList<UserProgressData> userProgressData = mySQLDatabase.getUserProgressData(ID);
            coins = userProgressData.get(0).getCoins();

            for(UserProgressData user: userProgressData)
            {
                if(user.getUserGameType().getVersus().getVersus().equals(MySQLDatabase.VS_COMPUTER))
                {
                    if(user.getUserGameType().getGameType().equals(MySQLDatabase.LUDO_CHALLENGE))
                    {
                        LudoChallenge_vsComputer_WIN = user.getUserGameType().getVersus().getWinAndLoses().getWins();
                        LudoChallenge_vsComputer_LOSE = user.getUserGameType().getVersus().getWinAndLoses().getLoses();
                    }
                    else if(user.getUserGameType().getGameType().equals(MySQLDatabase.SNAKES_AND_LADDERS))
                    {
                        SAL_vsComputer_WIN = user.getUserGameType().getVersus().getWinAndLoses().getWins();
                        SAL_vsComputer_LOSE = user.getUserGameType().getVersus().getWinAndLoses().getLoses();
                    }
                }
                else if(user.getUserGameType().getVersus().getVersus().equals(MySQLDatabase.VS_MULTIPLAYTER))
                {
                    if(user.getUserGameType().getGameType().equals(MySQLDatabase.LUDO_CHALLENGE))
                    {
                        LudoChallenge_vsMultiplayer_WIN = user.getUserGameType().getVersus().getWinAndLoses().getWins();
                        LudoChallenge_vsMultiplayer_LOSE = user.getUserGameType().getVersus().getWinAndLoses().getLoses();
                    }
                    else if(user.getUserGameType().getGameType().equals(MySQLDatabase.SNAKES_AND_LADDERS))
                    {
                        SAL_vsMultiplayer_WIN = user.getUserGameType().getVersus().getWinAndLoses().getWins();
                        SAL_vsMultiplayer_LOSE = user.getUserGameType().getVersus().getWinAndLoses().getLoses();
                    }
                }

            }


            play_coins.setText(coins);
            play_dialog_coins.setText(coins);
            play_dialog_LudoChallenge_vsComputer_win.setText(LudoChallenge_vsComputer_WIN);
            play_dialog_LudoChallenge_vsComputer_lose.setText(LudoChallenge_vsComputer_LOSE);
            play_dialog_LudoChallenge_vsMultiplayer_win.setText(LudoChallenge_vsMultiplayer_WIN);
            play_dialog_LudoChallenge_vsMultiplayer_lose.setText(LudoChallenge_vsMultiplayer_LOSE);
            play_dialog_SAL_vsComputer_win.setText(SAL_vsComputer_WIN);
            play_dialog_SAL_vsComputer_lose.setText(SAL_vsComputer_LOSE);
            play_dialog_SAL_vsMultiplayer_win.setText(SAL_vsMultiplayer_WIN);
            play_dialog_SAL_vsMultiplayer_lose.setText(SAL_vsMultiplayer_LOSE);
        }



        if(LOGIN_STATUS.equals(MySQLDatabase.LOGIN_STATUS_FACEBOOK)){
            Intent intent = getIntent();
            facebook_uid = intent.getStringExtra("facebook_uid");
            MySQLDatabase mySQLDatabase = MySQLDatabase.getInstance(getApplicationContext());

            facebook_display_name = (String) mySQLDatabase.getData(mySQLDatabase.fetchCurrentLoggedInID(), MySQLDatabase.NAME_USER, MySQLDatabase.FACEBOOK_USER_TABLE);
            facebook_country_name = (String) mySQLDatabase.getData(mySQLDatabase.fetchCurrentLoggedInID(), MySQLDatabase.NAME_FLAG, MySQLDatabase.FACEBOOK_USER_TABLE);
            facebook_photo_byte_array = (byte[]) mySQLDatabase.getData(mySQLDatabase.fetchCurrentLoggedInID(), MySQLDatabase.IMAGE_PROFILE_COL, MySQLDatabase.FACEBOOK_USER_TABLE);
            facebook_country_image = (byte[]) mySQLDatabase.getData(mySQLDatabase.fetchCurrentLoggedInID(), MySQLDatabase.PIC_FLAG, MySQLDatabase.FACEBOOK_USER_TABLE);

            userName.setText(facebook_display_name);
            edit_profile_dialog_userName.setText(facebook_display_name);
            countryName.setText(facebook_country_name);
            edit_profile_dialog_countryName.setText(facebook_country_name);
            Bitmap bitmap = BitmapFactory.decodeByteArray(facebook_photo_byte_array, 0, facebook_photo_byte_array.length);
            Bitmap bitmap1 = BitmapFactory.decodeByteArray(facebook_country_image, 0, facebook_country_image.length);
            editProfile_userImage.setImageBitmap(bitmap);
            profile_image.setImageBitmap(bitmap);
            flag_image.setImageBitmap(bitmap1);
            edit_profile_dialog_flagImage.setImageBitmap(bitmap1);
            play_editProfileDialog_edit_profileBtn.setVisibility(View.GONE);
            bitmap = null;
            bitmap1 = null;
        }



        if(LOGIN_STATUS.equals(MySQLDatabase.LOGIN_STATUS_LUDOCHALLENGE)) {
            byte[] d_profilePic = (byte[]) mySQLDatabase.getData(current_uid, MySQLDatabase.IMAGE_PROFILE_COL, MySQLDatabase.TABLE_NAME);
            byte[] d_flagPic = (byte[]) mySQLDatabase.getData(current_uid, MySQLDatabase.PIC_FLAG, MySQLDatabase.TABLE_NAME);
            String d_userName = (String) mySQLDatabase.getData(current_uid, MySQLDatabase.NAME_USER, MySQLDatabase.TABLE_NAME);
            String d_countryName = (String) mySQLDatabase.getData(current_uid, MySQLDatabase.NAME_FLAG, MySQLDatabase.TABLE_NAME);


            Bitmap bitmap = BitmapFactory.decodeByteArray(d_profilePic, 0, d_profilePic.length);
            profile_image.setImageBitmap(bitmap);
            editProfile_userImage.setImageBitmap(bitmap);
            bitmap = BitmapFactory.decodeByteArray(d_flagPic, 0, d_flagPic.length);
            flag_image.setImageBitmap(bitmap);
            edit_profile_dialog_flagImage.setImageBitmap(bitmap);
            userName.setText(d_userName);
            countryName.setText(d_countryName);
            edit_profile_dialog_userName.setText(d_userName);
            edit_profile_dialog_countryName.setText(d_countryName);
            bitmap = null;
        }

        if(LOGIN_STATUS.equals(MySQLDatabase.LOGIN_STATUS_PLAY_AS_GUEST)) {

            flag_image.setVisibility(View.GONE);
            edit_profile_dialog_flagImage.setVisibility(View.GONE);
            userName.setText("Guest");
            countryName.setVisibility(View.GONE);
            edit_profile_dialog_userName.setText("Guest");
            edit_profile_dialog_countryName.setVisibility(View.GONE);
            play_coins.setVisibility(View.GONE);
            play_coinIcon.setVisibility(View.GONE);
            play_editProfileDialog_edit_profileBtn.setVisibility(View.GONE);

        }

        online_multiplayer_4Players_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                online_multiplayer_coins_4players_dialog.setVisibility(View.GONE);
            }
        });

        online_multiplayer_play_another_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkOnlineCoinPlayers){
                    online_multiplayer_not_enough_coins_dialog.setVisibility(View.GONE);
                    online_multiplayer_coins_2players_dialog.setVisibility(View.VISIBLE);
                }
            }
        });
        online_multiplayer_get_more_coins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        online_multiplayer_2Players_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                online_multiplayer_coins_2players_dialog.setVisibility(View.GONE);
            }
        });


        online_multiplayer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableButtons();
                onlineMultiplayer_dialog.setVisibility(View.VISIBLE);

            }
        });

        online_multiplyer_dialog_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableButtons();
                onlineMultiplayer_dialog.setVisibility(View.GONE);
            }
        });
        online_multiplayer_two_players.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableButtons();
                onlineMultiplayer_dialog.setVisibility(View.GONE);
                online_multiplayer_coins_2players_dialog.setVisibility(View.VISIBLE);

            }
        });
        online_multiplayer_four_players.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableButtons();
                onlineMultiplayer_dialog.setVisibility(View.GONE);
                online_multiplayer_coins_4players_dialog.setVisibility(View.VISIBLE);
            }
        });

        multiplayer_coins_2players_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkCoin = Integer.parseInt(play_coins.getText().toString());
                if(checkCoin >= 100){
                    mySQLDatabase.incrementCoin(mySQLDatabase.fetchCurrentLoggedInID(), ""+ -100);
                    Intent intent = new Intent(getApplicationContext(), WaitingForOpponent2Players.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("online_multiplayer", true);
                    intent.putExtra("entry_coins", "100");
                    intent.putExtra("noOfPlayers","2");
                    startActivity(intent);
                    overridePendingTransition(R.anim.goup, R.anim.godown);
                    finish();

                }
                else{
                    //don't have enough coins
                    online_multiplayer_coins_2players_dialog.setVisibility(View.GONE);
                    online_multiplayer_not_enough_coins_dialog.setVisibility(View.VISIBLE);
                }
            }
        });
        multiplayer_coins_2players_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkCoin = Integer.parseInt(play_coins.getText().toString());
                if(checkCoin >= 500){
                    mySQLDatabase.incrementCoin(mySQLDatabase.fetchCurrentLoggedInID(), ""+ -500);
                    Intent intent = new Intent(getApplicationContext(), WaitingForOpponent2Players.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("online_multiplayer", true);
                    intent.putExtra("entry_coins", "500");
                    intent.putExtra("noOfPlayers","2");
                    startActivity(intent);
                    overridePendingTransition(R.anim.goup, R.anim.godown);
                    finish();

                }
                else{
                    //don't have enough coins
                    online_multiplayer_coins_2players_dialog.setVisibility(View.GONE);
                    online_multiplayer_not_enough_coins_dialog.setVisibility(View.VISIBLE);
                }
            }
        });
        multiplayer_coins_2players_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkCoin = Integer.parseInt(play_coins.getText().toString());
                if(checkCoin >= 5000){
                    mySQLDatabase.incrementCoin(mySQLDatabase.fetchCurrentLoggedInID(), ""+ -5000);
                    Intent intent = new Intent(getApplicationContext(), WaitingForOpponent2Players.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("online_multiplayer", true);
                    intent.putExtra("entry_coins", "5000");
                    intent.putExtra("noOfPlayers","2");
                    startActivity(intent);
                    overridePendingTransition(R.anim.goup, R.anim.godown);
                    finish();

                }
                else{
                    //don't have enough coins
                    online_multiplayer_coins_2players_dialog.setVisibility(View.GONE);
                    online_multiplayer_not_enough_coins_dialog.setVisibility(View.VISIBLE);
                }
            }
        });
        multiplayer_coins_2players_btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkCoin = Integer.parseInt(play_coins.getText().toString());
                if(checkCoin >= 25000){
                    mySQLDatabase.incrementCoin(mySQLDatabase.fetchCurrentLoggedInID(), ""+ -25000);
                    Intent intent = new Intent(getApplicationContext(), WaitingForOpponent2Players.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("online_multiplayer", true);
                    intent.putExtra("entry_coins", "25000");
                    intent.putExtra("noOfPlayers","2");
                    startActivity(intent);
                    overridePendingTransition(R.anim.goup, R.anim.godown);
                    finish();

                }
                else{
                    //don't have enough coins
                    online_multiplayer_coins_2players_dialog.setVisibility(View.GONE);
                    online_multiplayer_not_enough_coins_dialog.setVisibility(View.VISIBLE);
                }
            }
        });

        multiplayer_coins_2players_btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long checkCoin = Integer.parseInt(play_coins.getText().toString());
                if(checkCoin >= 50000){
                    mySQLDatabase.incrementCoin(mySQLDatabase.fetchCurrentLoggedInID(), ""+ -50000);
                    Intent intent = new Intent(getApplicationContext(), WaitingForOpponent2Players.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("online_multiplayer", true);
                    intent.putExtra("entry_coins", "50000");
                    intent.putExtra("noOfPlayers","2");
                    startActivity(intent);
                    overridePendingTransition(R.anim.goup, R.anim.godown);
                    finish();

                }
                else{
                    //don't have enough coins
                    online_multiplayer_coins_2players_dialog.setVisibility(View.GONE);
                    online_multiplayer_not_enough_coins_dialog.setVisibility(View.VISIBLE);
                }
            }
        });

        multiplayer_coins_2players_btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long checkCoin = Integer.parseInt(play_coins.getText().toString());
                if(checkCoin >= 100000){
                    mySQLDatabase.incrementCoin(mySQLDatabase.fetchCurrentLoggedInID(), ""+ -100000);
                    Intent intent = new Intent(getApplicationContext(), WaitingForOpponent2Players.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("online_multiplayer", true);
                    intent.putExtra("entry_coins", "100000");
                    intent.putExtra("noOfPlayers","2");
                    startActivity(intent);
                    overridePendingTransition(R.anim.goup, R.anim.godown);
                    finish();

                }
                else{
                    //don't have enough coins
                    online_multiplayer_coins_2players_dialog.setVisibility(View.GONE);
                    online_multiplayer_not_enough_coins_dialog.setVisibility(View.VISIBLE);
                }
            }
        });
        multiplayer_coins_2players_btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long checkCoin = Integer.parseInt(play_coins.getText().toString());
                if(checkCoin >= 250000){
                    mySQLDatabase.incrementCoin(mySQLDatabase.fetchCurrentLoggedInID(), ""+ -250000);
                    Intent intent = new Intent(getApplicationContext(), WaitingForOpponent2Players.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("online_multiplayer", true);
                    intent.putExtra("entry_coins", "250000");
                    intent.putExtra("noOfPlayers","2");
                    startActivity(intent);
                    overridePendingTransition(R.anim.goup, R.anim.godown);
                    finish();

                }
                else{
                    //don't have enough coins
                    online_multiplayer_coins_2players_dialog.setVisibility(View.GONE);
                    online_multiplayer_not_enough_coins_dialog.setVisibility(View.VISIBLE);
                }
            }
        });
        multiplayer_coins_2players_btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long checkCoin = Integer.parseInt(play_coins.getText().toString());
                if(checkCoin >= 500000){
                    mySQLDatabase.incrementCoin(mySQLDatabase.fetchCurrentLoggedInID(), ""+ -500000);
                    Intent intent = new Intent(getApplicationContext(), WaitingForOpponent2Players.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("online_multiplayer", true);
                    intent.putExtra("entry_coins", "500000");
                    intent.putExtra("noOfPlayers","2");
                    startActivity(intent);
                    overridePendingTransition(R.anim.goup, R.anim.godown);
                    finish();

                }
                else{
                    //don't have enough coins
                    online_multiplayer_coins_2players_dialog.setVisibility(View.GONE);
                    online_multiplayer_not_enough_coins_dialog.setVisibility(View.VISIBLE);
                }
            }
        });
        multiplayer_coins_2players_btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long checkCoin = Integer.parseInt(play_coins.getText().toString());
                if(checkCoin >= 750000){
                    mySQLDatabase.incrementCoin(mySQLDatabase.fetchCurrentLoggedInID(), ""+ -750000);
                    Intent intent = new Intent(getApplicationContext(), WaitingForOpponent2Players.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("online_multiplayer", true);
                    intent.putExtra("entry_coins", "750000");
                    intent.putExtra("noOfPlayers","2");
                    startActivity(intent);
                    overridePendingTransition(R.anim.goup, R.anim.godown);
                    finish();

                }
                else{
                    //don't have enough coins
                    online_multiplayer_coins_2players_dialog.setVisibility(View.GONE);
                    online_multiplayer_not_enough_coins_dialog.setVisibility(View.VISIBLE);
                }
            }
        });



        online_multiplayer_coins_4players_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkCoin = Integer.parseInt(play_coins.getText().toString());
                if(checkCoin >= 100){
                    mySQLDatabase.incrementCoin(mySQLDatabase.fetchCurrentLoggedInID(), ""+ -100);
                    Intent intent = new Intent(getApplicationContext(), WaitingForOpponent4Players.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("online_multiplayer", true);
                    intent.putExtra("entry_coins", "100");
                    intent.putExtra("noOfPlayers","4");
                    startActivity(intent);
                    overridePendingTransition(R.anim.goup, R.anim.godown);
                    finish();

                }
                else{
                    //don't have enough coins
                    online_multiplayer_coins_2players_dialog.setVisibility(View.GONE);
                    online_multiplayer_not_enough_coins_dialog.setVisibility(View.VISIBLE);
                }
            }
        });


        online_multiplayer_coins_4players_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkCoin = Integer.parseInt(play_coins.getText().toString());
                if(checkCoin >= 500){
                    mySQLDatabase.incrementCoin(mySQLDatabase.fetchCurrentLoggedInID(), ""+ -500);
                    Intent intent = new Intent(getApplicationContext(), WaitingForOpponent4Players.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("online_multiplayer", true);
                    intent.putExtra("entry_coins", "500");
                    intent.putExtra("noOfPlayers","4");
                    startActivity(intent);
                    overridePendingTransition(R.anim.goup, R.anim.godown);
                    finish();

                }
                else{
                    //don't have enough coins
                    online_multiplayer_coins_2players_dialog.setVisibility(View.GONE);
                    online_multiplayer_not_enough_coins_dialog.setVisibility(View.VISIBLE);
                }
            }
        });

        online_multiplayer_coins_4players_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkCoin = Integer.parseInt(play_coins.getText().toString());
                if(checkCoin >= 5000){
                    mySQLDatabase.incrementCoin(mySQLDatabase.fetchCurrentLoggedInID(), ""+ -5000);
                    Intent intent = new Intent(getApplicationContext(), WaitingForOpponent4Players.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("online_multiplayer", true);
                    intent.putExtra("entry_coins", "5000");
                    intent.putExtra("noOfPlayers","4");
                    startActivity(intent);
                    overridePendingTransition(R.anim.goup, R.anim.godown);
                    finish();

                }
                else{
                    //don't have enough coins
                    online_multiplayer_coins_2players_dialog.setVisibility(View.GONE);
                    online_multiplayer_not_enough_coins_dialog.setVisibility(View.VISIBLE);
                }

            }
        });

        online_multiplayer_coins_4players_btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkCoin = Integer.parseInt(play_coins.getText().toString());
                if(checkCoin >= 25000){
                    mySQLDatabase.incrementCoin(mySQLDatabase.fetchCurrentLoggedInID(), ""+ -25000);
                    Intent intent = new Intent(getApplicationContext(), WaitingForOpponent4Players.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("online_multiplayer", true);
                    intent.putExtra("entry_coins", "25000");
                    intent.putExtra("noOfPlayers","4");
                    startActivity(intent);
                    overridePendingTransition(R.anim.goup, R.anim.godown);
                    finish();

                }
                else{
                    //don't have enough coins
                    online_multiplayer_coins_2players_dialog.setVisibility(View.GONE);
                    online_multiplayer_not_enough_coins_dialog.setVisibility(View.VISIBLE);
                }
            }
        });

        online_multiplayer_coins_4players_btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkCoin = Integer.parseInt(play_coins.getText().toString());
                if(checkCoin >= 50000){
                    mySQLDatabase.incrementCoin(mySQLDatabase.fetchCurrentLoggedInID(), ""+ -50000);
                    Intent intent = new Intent(getApplicationContext(), WaitingForOpponent4Players.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("online_multiplayer", true);
                    intent.putExtra("entry_coins", "50000");
                    intent.putExtra("noOfPlayers","4");
                    startActivity(intent);
                    overridePendingTransition(R.anim.goup, R.anim.godown);
                    finish();

                }
                else{
                    //don't have enough coins
                    online_multiplayer_coins_2players_dialog.setVisibility(View.GONE);
                    online_multiplayer_not_enough_coins_dialog.setVisibility(View.VISIBLE);
                }
            }
        });
        online_multiplayer_coins_4players_btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkCoin = Integer.parseInt(play_coins.getText().toString());
                if(checkCoin >= 100000){
                    mySQLDatabase.incrementCoin(mySQLDatabase.fetchCurrentLoggedInID(), ""+ -100000);
                    Intent intent = new Intent(getApplicationContext(), WaitingForOpponent4Players.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("online_multiplayer", true);
                    intent.putExtra("entry_coins", "100000");
                    intent.putExtra("noOfPlayers","4");
                    startActivity(intent);
                    overridePendingTransition(R.anim.goup, R.anim.godown);
                    finish();

                }
                else{
                    //don't have enough coins
                    online_multiplayer_coins_2players_dialog.setVisibility(View.GONE);
                    online_multiplayer_not_enough_coins_dialog.setVisibility(View.VISIBLE);
                }
            }
        });
        online_multiplayer_coins_4players_btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkCoin = Integer.parseInt(play_coins.getText().toString());
                if(checkCoin >= 250000){
                    mySQLDatabase.incrementCoin(mySQLDatabase.fetchCurrentLoggedInID(), ""+ -250000);
                    Intent intent = new Intent(getApplicationContext(), WaitingForOpponent4Players.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("online_multiplayer", true);
                    intent.putExtra("entry_coins", "250000");
                    intent.putExtra("noOfPlayers","4");
                    startActivity(intent);
                    overridePendingTransition(R.anim.goup, R.anim.godown);
                    finish();

                }
                else{
                    //don't have enough coins
                    online_multiplayer_coins_2players_dialog.setVisibility(View.GONE);
                    online_multiplayer_not_enough_coins_dialog.setVisibility(View.VISIBLE);
                }
            }
        });
        online_multiplayer_coins_4players_btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkCoin = Integer.parseInt(play_coins.getText().toString());
                if(checkCoin >= 500000){
                    mySQLDatabase.incrementCoin(mySQLDatabase.fetchCurrentLoggedInID(), ""+ -500000);
                    Intent intent = new Intent(getApplicationContext(), WaitingForOpponent4Players.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("online_multiplayer", true);
                    intent.putExtra("entry_coins", "500000");
                    intent.putExtra("noOfPlayers","4");
                    startActivity(intent);
                    overridePendingTransition(R.anim.goup, R.anim.godown);
                    finish();

                }
                else{
                    //don't have enough coins
                    online_multiplayer_coins_2players_dialog.setVisibility(View.GONE);
                    online_multiplayer_not_enough_coins_dialog.setVisibility(View.VISIBLE);
                }
            }
        });
        online_multiplayer_coins_4players_btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkCoin = Integer.parseInt(play_coins.getText().toString());
                if(checkCoin >= 750000){
                    mySQLDatabase.incrementCoin(mySQLDatabase.fetchCurrentLoggedInID(), ""+ -750000);
                    Intent intent = new Intent(getApplicationContext(), WaitingForOpponent4Players.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("online_multiplayer", true);
                    intent.putExtra("entry_coins", "750000");
                    intent.putExtra("noOfPlayers","4");
                    startActivity(intent);
                    overridePendingTransition(R.anim.goup, R.anim.godown);
                    finish();

                }
                else{
                    //don't have enough coins
                    online_multiplayer_coins_2players_dialog.setVisibility(View.GONE);
                    online_multiplayer_not_enough_coins_dialog.setVisibility(View.VISIBLE);
                }
            }
        });




        play_backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.goup, R.anim.godown);
                finish();
            }
        });

        playWithFriends_backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableButtons();
                playWithFriends_dialogBox.setVisibility(View.GONE);
            }
        });

        playWithFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(LOGIN_STATUS.equals(MySQLDatabase.LOGIN_STATUS_FACEBOOK) || LOGIN_STATUS.equals(MySQLDatabase.LOGIN_STATUS_LUDOCHALLENGE)) {
                    disableButtons();
                    if(interstitialAd.isLoaded()){
                        interstitialAd.show();
                    }
                    else{
                        if (LOGIN_STATUS.equals(MySQLDatabase.LOGIN_STATUS_FACEBOOK)) {
                            Intent intent = new Intent(getApplicationContext(), UsersActivity.class);
                            intent.putExtra("activity", UsersActivity.class.getName());
                            intent.putExtra("noOfPlayers", noOfPlayers);
                            intent.putExtra("color", color);
                            intent.putExtra("vsComputer", vsComputer);
                            intent.putExtra("facebook_uid", facebook_uid);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            overridePendingTransition(R.anim.goup, R.anim.godown);
                        } else {
                            playWithFriends_dialogBox.setVisibility(View.VISIBLE);
                        }
                    }
                }
                else{
                    TastyToast.makeText(getApplicationContext(),"Sign in to play with your friends!", TastyToast.LENGTH_SHORT, TastyToast.INFO).show();
                }
            }
        });

        playWithFriends_2players.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noOfPlayers = 2;
                color = 3;
                vsComputer = 0;
                playWithFriends_dialogBox.setVisibility(View.GONE);
                Intent intent = new Intent(getApplicationContext(), UsersActivity.class);
                intent.putExtra("noOfPlayers",noOfPlayers);
                intent.putExtra("color",color);
                intent.putExtra("vsComputer",vsComputer);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.goup, R.anim.godown);
            }
        });

        three_players_playWithFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noOfPlayers = 3;
                color = 3;
                vsComputer = 0;
                playWithFriends_dialogBox.setVisibility(View.GONE);
                Intent intent = new Intent(getApplicationContext(), UsersActivityThreePlayers.class);
                intent.putExtra("noOfPlayers",noOfPlayers);
                intent.putExtra("color",color);
                intent.putExtra("vsComputer",vsComputer);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.goup, R.anim.godown);
            }
        });

        four_players_playWithFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noOfPlayers = 4;
                color = 3;
                vsComputer = 0;
                playWithFriends_dialogBox.setVisibility(View.GONE);
                Intent intent = new Intent(getApplicationContext(), UsersActivityThreePlayers.class);
                intent.putExtra("noOfPlayers",noOfPlayers);
                intent.putExtra("color",color);
                intent.putExtra("vsComputer",vsComputer);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.goup, R.anim.godown);
            }
        });


        play_editProfileDialog_edit_profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_profile_dialogBox.setVisibility(View.GONE);
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.goup, R.anim.godown);
                finish();
            }
        });







        profile_picBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!LOGIN_STATUS.equals(MySQLDatabase.LOGIN_STATUS_PLAY_AS_GUEST)) {
                    disableButtons();
                    edit_profile_dialogBox.setVisibility(View.VISIBLE);
                }
            }
        });
        edit_profile_dilaogBox_backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_profile_dialogBox.setVisibility(View.GONE);
                enableButtons();
            }
        });

        local_multiplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disableButtons();
                frameLayout.setVisibility(View.VISIBLE);
            }
        });

        vsComputer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disableButtons();
                vsComputer_frameLayout.setVisibility(View.VISIBLE);
            }
        });

        vsComputer_backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableButtons();
                vsComputer_frameLayout.setVisibility(View.GONE);
            }
        });
        selectNoOfPlayers_backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableButtons();
                frameLayout.setVisibility(View.GONE);
            }
        });

        four_players.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameLayout.setVisibility(View.GONE);
                four_player_frameLayout.setVisibility(View.VISIBLE);
            }
        });
        three_players.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameLayout.setVisibility(View.GONE);
                three_player_frameLayout.setVisibility(View.VISIBLE);
            }
        });
        two_players.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameLayout.setVisibility(View.GONE);
                two_player_frameLayout.setVisibility(View.VISIBLE);
            }
        });
        four_players_backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                four_player_frameLayout.setVisibility(View.GONE);
                enableButtons();
            }
        });
        three_players_backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableButtons();
                three_player_frameLayout.setVisibility(View.GONE);
            }
        });

        two_players_backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableButtons();
                two_player_frameLayout.setVisibility(View.GONE);
            }
        });

        four_players_playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                colors_array = new int[]{Color.getInt(Color.RED), Color.getInt(Color.BLUE), Color.getInt(Color.YELLOW), Color.getInt(Color.GREEN)};
                noOfPlayers = 4;
                color = 0;
                String player1_name = four_players_player1_name.getText().toString();
                String player2_name = four_players_player2_name.getText().toString();
                String player3_name = four_players_player3_name.getText().toString();
                String player4_name = four_players_player4_name.getText().toString();

                if(TextUtils.isEmpty(player1_name) || TextUtils.isEmpty(player2_name) || TextUtils.isEmpty(player3_name) || TextUtils.isEmpty(player4_name)){
                    TastyToast.makeText(getApplicationContext(),"Please enter name!", TastyToast.LENGTH_SHORT, TastyToast.INFO).show();
                }
                else{
                    four_player_frameLayout.setVisibility(View.GONE);
                    Intent intent = new Intent(getApplicationContext(), LudoActivity.class);
                    String[] player_names = new String[4];
                    player_names[0] = player1_name;
                    player_names[1] = player2_name;
                    player_names[2] = player3_name;
                    player_names[3] = player4_name;
                    int[] player_types = new int[]{PlayerType.getInt(PlayerType.HUMAN), PlayerType.getInt(PlayerType.HUMAN), PlayerType.getInt(PlayerType.HUMAN), PlayerType.getInt(PlayerType.HUMAN)};
                    intent.putExtra(PLAYERS_KEY,noOfPlayers);
                    intent.putExtra(COLORS_KEY,colors_array);
                    intent.putExtra(PLAYERS_TYPE_KEY, player_types);
                    intent.putExtra(NAMES_KEY,player_names);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    overridePendingTransition(R.anim.goup, R.anim.godown);
                    four_players_player1_name.setText("");
                    four_players_player2_name.setText("");
                    four_players_player3_name.setText("");
                    four_players_player4_name.setText("");
                    finish();
                }
            }
        });

        three_players_playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!check_3player_localClick){
                    colors_array = new int[]{Color.getInt(Color.GREEN), Color.getInt(Color.RED), Color.getInt(Color.BLUE)};
                }
                noOfPlayers = 3;
                String player1_name = three_players_player1_name.getText().toString();
                String player2_name = three_players_player2_name.getText().toString();
                String player3_name = three_players_player3_name.getText().toString();

                if(TextUtils.isEmpty(player1_name) || TextUtils.isEmpty(player2_name) || TextUtils.isEmpty(player3_name)){
                    TastyToast.makeText(getApplicationContext(),"Please enter name!", TastyToast.LENGTH_SHORT, TastyToast.INFO).show();
                }
                else{
                    three_player_frameLayout.setVisibility(View.GONE);
                    Intent intent = new Intent(getApplicationContext(), LudoActivity.class);
                    String[] player_names = new String[3];
                    player_names[0] = player1_name;
                    player_names[1] = player2_name;
                    player_names[2] = player3_name;
                    int[] player_types = new int[]{PlayerType.getInt(PlayerType.HUMAN), PlayerType.getInt(PlayerType.HUMAN), PlayerType.getInt(PlayerType.HUMAN)};
                    intent.putExtra(PLAYERS_KEY,noOfPlayers);
                    intent.putExtra(COLORS_KEY,colors_array);
                    intent.putExtra(PLAYERS_TYPE_KEY, player_types);
                    intent.putExtra(NAMES_KEY,player_names);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    overridePendingTransition(R.anim.goup, R.anim.godown);
                    three_players_player1_name.setText("");
                    three_players_player2_name.setText("");
                    three_players_player3_name.setText("");
                    finish();
                }
            }
        });

        two_players_playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noOfPlayers = 2;
                String player1_name = two_players_player1_name.getText().toString();
                String player2_name = two_players_player2_name.getText().toString();


                if(TextUtils.isEmpty(player1_name) || TextUtils.isEmpty(player2_name)){
                    TastyToast.makeText(getApplicationContext(),"Please enter name!", TastyToast.LENGTH_SHORT, TastyToast.INFO).show();
                }
                else{
                    two_player_frameLayout.setVisibility(View.GONE);
                    Intent intent = new Intent(getApplicationContext(), LudoActivity.class);
                    String[] player_names = new String[2];
                    player_names[0] = player1_name;
                    player_names[1] = player2_name;
                    int[] player_types = new int[]{PlayerType.getInt(PlayerType.HUMAN), PlayerType.getInt(PlayerType.HUMAN)};
                    intent.putExtra(PLAYERS_KEY,noOfPlayers);
                    intent.putExtra(COLORS_KEY,colors_array);
                    intent.putExtra(PLAYERS_TYPE_KEY, player_types);
                    intent.putExtra(NAMES_KEY,player_names);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    overridePendingTransition(R.anim.goup, R.anim.godown);
                    two_players_player1_name.setText("");
                    two_players_player2_name.setText("");
                    finish();
                }
            }
        });
        vsComputer_playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    noOfPlayers = 2;
                    vsComputer = 1;
                    if(!check_2player_computerClick) {
                        colors_array = new int[]{Color.getInt(Color.RED), Color.getInt(Color.YELLOW)};
                    }
                    String[] player_names = new String[2];
                    String player1_name = null;
                    String player2_name = null;
                    player_names[0] = player1_name;
                    player_names[1] = player2_name;
                    int[] player_types = new int[]{PlayerType.getInt(PlayerType.HUMAN), PlayerType.getInt(PlayerType.CPU)};
                    vsComputer_frameLayout.setVisibility(View.GONE);
                    Intent intent = new Intent(getApplicationContext(), LudoActivity.class);
                    intent.putExtra(PLAYERS_KEY,noOfPlayers);
                    intent.putExtra(COLORS_KEY,colors_array);
                    intent.putExtra(NAMES_KEY,player_names);
                    intent.putExtra(PLAYERS_TYPE_KEY, player_types);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                overridePendingTransition(R.anim.goup, R.anim.godown);
                    finish();
                }
        });

        setColor();
        vsComputer();
    }


    void setColor(){
        two_players_leftPress1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkPawn == 0) {
                    two_players_pawn1.setImageResource(R.drawable.select_your_color_blue_pawn);
                    two_players_pawn2.setImageResource(R.drawable.select_your_color_green_pawn);
                    checkPawn = 1;
                    colors_array = new int[]{Color.getInt(Color.BLUE), Color.getInt(Color.GREEN)};
                }
                else if(checkPawn == 1) {
                    two_players_pawn1.setImageResource(R.drawable.select_your_color_red_pawn);
                    two_players_pawn2.setImageResource(R.drawable.select_your_color_yellow_pawn);
                    checkPawn = 0;
                    checkPawn = 0;
                    colors_array = new int[]{Color.getInt(Color.RED), Color.getInt(Color.YELLOW)};
                }
            }
        });
        two_players_rightPress1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkPawn == 0) {
                    two_players_pawn1.setImageResource(R.drawable.select_your_color_blue_pawn);
                    two_players_pawn2.setImageResource(R.drawable.select_your_color_green_pawn);
                    checkPawn = 1;
                    colors_array = new int[]{Color.getInt(Color.BLUE), Color.getInt(Color.GREEN)};
                }
                else if(checkPawn == 1) {
                    two_players_pawn1.setImageResource(R.drawable.select_your_color_red_pawn);
                    two_players_pawn2.setImageResource(R.drawable.select_your_color_yellow_pawn);
                    checkPawn = 0;
                    checkPawn = 0;
                    colors_array = new int[]{Color.getInt(Color.RED), Color.getInt(Color.YELLOW)};
                }
            }
        });
        two_players_leftPress2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkPawn == 0) {
                    two_players_pawn1.setImageResource(R.drawable.select_your_color_blue_pawn);
                    two_players_pawn2.setImageResource(R.drawable.select_your_color_green_pawn);
                    colors_array = new int[]{Color.getInt(Color.BLUE), Color.getInt(Color.GREEN)};
                    checkPawn = 1;
                }
                else if(checkPawn == 1) {
                    two_players_pawn1.setImageResource(R.drawable.select_your_color_red_pawn);
                    two_players_pawn2.setImageResource(R.drawable.select_your_color_yellow_pawn);
                    colors_array = new int[]{Color.getInt(Color.RED), Color.getInt(Color.YELLOW)};
                    checkPawn = 0;
                }
            }
        });
        two_players_rightPress2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkPawn == 0) {
                    two_players_pawn1.setImageResource(R.drawable.select_your_color_blue_pawn);
                    two_players_pawn2.setImageResource(R.drawable.select_your_color_green_pawn);
                    colors_array = new int[]{Color.getInt(Color.BLUE), Color.getInt(Color.GREEN)};
                    checkPawn = 1;
                }
                else if(checkPawn == 1) {
                    two_players_pawn1.setImageResource(R.drawable.select_your_color_red_pawn);
                    two_players_pawn2.setImageResource(R.drawable.select_your_color_yellow_pawn);
                    colors_array = new int[]{Color.getInt(Color.RED), Color.getInt(Color.YELLOW)};
                    checkPawn = 0;
                }
            }
        });

        three_players_leftPress1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_3player_localClick = true;
                if(checkPawn_3players == 0){
                    three_players_pawn1.setImageResource(R.drawable.select_your_color_yellow_pawn);
                    three_players_pawn2.setImageResource(R.drawable.select_your_color_green_pawn);
                    three_players_pawn3.setImageResource(R.drawable.select_your_color_red_pawn);
                    checkPawn_3players = 1;
                    colors_array = new int[]{Color.getInt(Color.YELLOW), Color.getInt(Color.GREEN), Color.getInt(Color.RED)};
                }
                else if(checkPawn_3players == 1){
                    three_players_pawn1.setImageResource(R.drawable.select_your_color_red_pawn);
                    three_players_pawn2.setImageResource(R.drawable.select_your_color_blue_pawn);
                    three_players_pawn3.setImageResource(R.drawable.select_your_color_yellow_pawn);
                    checkPawn_3players = 0;
                    color_3players = 2;
                    colors_array = new int[]{Color.getInt(Color.GREEN), Color.getInt(Color.RED), Color.getInt(Color.BLUE)};
                }

            }
        });
        three_players_leftPress2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_3player_localClick = true;
                if(checkPawn_3players == 0){
                    three_players_pawn1.setImageResource(R.drawable.select_your_color_yellow_pawn);
                    three_players_pawn2.setImageResource(R.drawable.select_your_color_green_pawn);
                    three_players_pawn3.setImageResource(R.drawable.select_your_color_red_pawn);
                    checkPawn_3players = 1;
                    color_3players = 4;
                    colors_array = new int[]{Color.getInt(Color.YELLOW), Color.getInt(Color.GREEN), Color.getInt(Color.RED)};
                }
                else if(checkPawn_3players == 1){
                    three_players_pawn1.setImageResource(R.drawable.select_your_color_green_pawn);
                    three_players_pawn2.setImageResource(R.drawable.select_your_color_red_pawn);
                    three_players_pawn3.setImageResource(R.drawable.select_your_color_blue_pawn);
                    checkPawn_3players = 0;
                    color_3players = 2;
                    colors_array = new int[]{Color.getInt(Color.GREEN), Color.getInt(Color.RED), Color.getInt(Color.BLUE)};
                }
            }
        });
        three_players_leftPress3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_3player_localClick = true;
                if(checkPawn_3players == 0){
                    three_players_pawn1.setImageResource(R.drawable.select_your_color_yellow_pawn);
                    three_players_pawn2.setImageResource(R.drawable.select_your_color_green_pawn);
                    three_players_pawn3.setImageResource(R.drawable.select_your_color_red_pawn);
                    checkPawn_3players = 1;
                    color_3players = 4;
                    colors_array = new int[]{Color.getInt(Color.YELLOW), Color.getInt(Color.GREEN), Color.getInt(Color.RED)};
                }
                else if(checkPawn_3players == 1){
                    three_players_pawn1.setImageResource(R.drawable.select_your_color_green_pawn);
                    three_players_pawn2.setImageResource(R.drawable.select_your_color_red_pawn);
                    three_players_pawn3.setImageResource(R.drawable.select_your_color_blue_pawn);
                    checkPawn_3players = 0;
                    color_3players = 2;
                    colors_array = new int[]{Color.getInt(Color.GREEN), Color.getInt(Color.RED), Color.getInt(Color.BLUE)};
                }
            }
        });
        three_players_rightPress1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_3player_localClick = true;
                if(checkPawn_3players == 0){
                    three_players_pawn1.setImageResource(R.drawable.select_your_color_yellow_pawn);
                    three_players_pawn2.setImageResource(R.drawable.select_your_color_green_pawn);
                    three_players_pawn3.setImageResource(R.drawable.select_your_color_red_pawn);
                    checkPawn_3players = 1;
                    color_3players = 4;
                    colors_array = new int[]{Color.getInt(Color.YELLOW), Color.getInt(Color.GREEN), Color.getInt(Color.RED)};
                }
                else if(checkPawn_3players == 1){
                    three_players_pawn1.setImageResource(R.drawable.select_your_color_green_pawn);
                    three_players_pawn2.setImageResource(R.drawable.select_your_color_red_pawn);
                    three_players_pawn3.setImageResource(R.drawable.select_your_color_blue_pawn);
                    checkPawn_3players = 0;
                    color_3players = 2;
                    colors_array = new int[]{Color.getInt(Color.GREEN), Color.getInt(Color.RED), Color.getInt(Color.BLUE)};
                }
            }
        });
        three_players_rightPress2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_3player_localClick = true;
                if(checkPawn_3players == 0){
                    three_players_pawn1.setImageResource(R.drawable.select_your_color_yellow_pawn);
                    three_players_pawn2.setImageResource(R.drawable.select_your_color_green_pawn);
                    three_players_pawn3.setImageResource(R.drawable.select_your_color_red_pawn);
                    checkPawn_3players = 1;
                    color_3players = 4;
                    colors_array = new int[]{Color.getInt(Color.YELLOW), Color.getInt(Color.GREEN), Color.getInt(Color.RED)};
                }
                else if(checkPawn_3players == 1){
                    three_players_pawn1.setImageResource(R.drawable.select_your_color_green_pawn);
                    three_players_pawn2.setImageResource(R.drawable.select_your_color_red_pawn);
                    three_players_pawn3.setImageResource(R.drawable.select_your_color_blue_pawn);
                    checkPawn_3players = 0;
                    color_3players = 2;
                    colors_array = new int[]{Color.getInt(Color.GREEN), Color.getInt(Color.RED), Color.getInt(Color.BLUE)};
                }
            }
        });
        three_players_rightPress3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_3player_localClick = true;
                if(checkPawn_3players == 0){
                    three_players_pawn1.setImageResource(R.drawable.select_your_color_yellow_pawn);
                    three_players_pawn2.setImageResource(R.drawable.select_your_color_green_pawn);
                    three_players_pawn3.setImageResource(R.drawable.select_your_color_red_pawn);
                    checkPawn_3players = 1;
                    color_3players = 4;
                    colors_array = new int[]{Color.getInt(Color.YELLOW), Color.getInt(Color.GREEN), Color.getInt(Color.RED)};
                }
                else if(checkPawn_3players == 1){
                    three_players_pawn1.setImageResource(R.drawable.select_your_color_green_pawn);
                    three_players_pawn2.setImageResource(R.drawable.select_your_color_red_pawn);
                    three_players_pawn3.setImageResource(R.drawable.select_your_color_blue_pawn);
                    checkPawn_3players = 0;
                    color_3players = 2;
                    colors_array = new int[]{Color.getInt(Color.GREEN), Color.getInt(Color.RED), Color.getInt(Color.BLUE)};
                }
            }
        });
    }

    void vsComputer(){
        vsComputer_check1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vsComputer_check1.setImageResource(R.drawable.circle_tick);
                vsComputer_check2.setImageResource(R.drawable.dull_circle);
                vsComputer_check3.setImageResource(R.drawable.dull_circle);
                vsComputer_check4.setImageResource(R.drawable.dull_circle);
                colors_array = new int[]{Color.getInt(Color.RED), Color.getInt(Color.YELLOW)};
                check_2player_computerClick = true;
            }
        });
        vsComputer_check2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vsComputer_check1.setImageResource(R.drawable.dull_circle);
                vsComputer_check2.setImageResource(R.drawable.circle_tick);
                vsComputer_check3.setImageResource(R.drawable.dull_circle);
                vsComputer_check4.setImageResource(R.drawable.dull_circle);
                vsComputer_color = 3;
                colors_array = new int[]{Color.getInt(Color.BLUE), Color.getInt(Color.GREEN)};
                check_2player_computerClick = true;
            }
        });
        vsComputer_check3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vsComputer_check1.setImageResource(R.drawable.dull_circle);
                vsComputer_check2.setImageResource(R.drawable.dull_circle);
                vsComputer_check3.setImageResource(R.drawable.circle_tick);
                vsComputer_check4.setImageResource(R.drawable.dull_circle);
                vsComputer_color = 2;
                colors_array = new int[]{Color.getInt(Color.GREEN), Color.getInt(Color.BLUE)};
                check_2player_computerClick = true;
            }
        });
        vsComputer_check4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vsComputer_check1.setImageResource(R.drawable.dull_circle);
                vsComputer_check2.setImageResource(R.drawable.dull_circle);
                vsComputer_check3.setImageResource(R.drawable.dull_circle);
                vsComputer_check4.setImageResource(R.drawable.circle_tick);
                vsComputer_color = 0;
                colors_array = new int[]{Color.getInt(Color.YELLOW), Color.getInt(Color.RED)};
                check_2player_computerClick = true;
            }
        });
    }


    private void disableButtons(){
        profile_picBox.setEnabled(false);
        local_multiplayer.setEnabled(false);
        vsComputer_btn.setEnabled(false);
        playWithFriends.setEnabled(false);
    }
    private void enableButtons(){
        profile_picBox.setEnabled(true);
        local_multiplayer.setEnabled(true);
        vsComputer_btn.setEnabled(true);
        playWithFriends.setEnabled(true);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainMenu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.goup, R.anim.godown);
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        logoDrawable.stop();
//        logoDrawable.setCallback(null);
//        logoDrawable.setOneShot(true);
        unbindDrawables(findViewById(R.id.playActivityRoot));
//        freeLogo(logoDrawable);
//        logoDrawable = null;
        Runtime.getRuntime().gc();
        System.gc();
    }

    private void freeLogo(AnimationDrawable drawable)
    {
        drawable.mutate();
        drawable.stop();
        for(int i = 0; i < drawable.getNumberOfFrames(); i++)
        {
            BitmapDrawable drawable1 = (BitmapDrawable) drawable.getFrame(i);
            drawable1.mutate();
            drawable1.getBitmap().recycle();
            drawable1.setCallback(null);
        }
        drawable.setCallback(null);
    }

    private void unbindDrawables(View view) {
        if (view.getBackground() != null)
            view.getBackground().setCallback(null);

        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            imageView.setImageBitmap(null);
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++)
                unbindDrawables(viewGroup.getChildAt(i));

            if (!(view instanceof AdapterView))
                viewGroup.removeAllViews();
        }
    }

}
