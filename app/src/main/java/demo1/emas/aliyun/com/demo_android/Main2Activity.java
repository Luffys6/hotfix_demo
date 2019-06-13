package demo1.emas.aliyun.com.demo_android;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

public class Main2Activity extends AppCompatActivity {
    private MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        findViewById(R.id.complete_shiting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //测试新增资源  播放本地新增资源无效  官方给的解释是MediaPlayer不会找补丁中资源
//                mp = MediaPlayer.create(Main2Activity.this, R.raw.djs);
//                mp.start();    "https://flv.m.xueceping.cn/upload_media/yuwen/Videos/115b54da-e38d-42d1-8a12-4dfac81697b9.mp3"
//                MediaPlayer mp = new MediaPlayer();
//                AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.djs);
//                try {
//                    mp.setDataSource(file.getFileDescriptor(), file.getStartOffset(),
//                            file.getLength());
//                    mp.prepare();
//                    file.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                mp.setVolume(0.5f, 0.5f);
//                mp.start();
                //测试新增资源  播放本地新增资源无效
//                SoundPool soundPool=new SoundPool(100, AudioManager.STREAM_MUSIC,5);//构建对象
//                soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
//                    @Override
//                    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
//                        soundPool.play(sampleId,1,1,1,0,1);//播放
//                    }
//                });
//                soundPool.load(Main2Activity.this,R.raw.djs,1);//加载资源
                initAudio();
                //测试新增资源  播放网络音频有效
                playAudio("https://flv.m.xueceping.cn/upload_media/yuwen/Videos/115b54da-e38d-42d1-8a12-4dfac81697b9.mp3");

            }
        });
    }
    //未完成状态播放录音
    private void playAudio(String audioUrl) {
        try {
            Uri uri = Uri.parse(audioUrl);
            mp.reset();
            mp.setDataSource(Main2Activity.this,uri);
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //未完成状态初始化播放器
    private void initAudio() {
        if (mp == null) {
            mp = new MediaPlayer();
        }
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(Main2Activity.this,"已经修复播放了哦",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
