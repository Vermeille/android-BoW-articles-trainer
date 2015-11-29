package gcm.play.android.samples.com.gcmquickstart;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import java.io.IOException;
import java.net.URI;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;


public class VoteActivity extends AppCompatActivity {

    private static final String VOTE_URL = "MY_SERVER:8888";
    private static AsyncHttpClient mClient = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
        TextView titleView = (TextView) findViewById(R.id.title);
        Button browser = (Button) findViewById(R.id.browse);
        Button cool = (Button) findViewById(R.id.cool);
        Button shit = (Button) findViewById(R.id.shit);

        mClient.addHeader("Accept", "application/json");

        final String title = getIntent().getStringExtra("title");
        titleView.setText(title);
        browser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = getIntent().getStringExtra("url");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        cool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vote(title, "cool");
            }
        });

        shit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vote(title, "shit");
            }
        });
    }

    void vote(String input, String label) {
        RequestParams params = new RequestParams();
        params.add("label", label);
        params.add("epoch", "10");
        params.add("input", input);
        mClient.put("http://" + VOTE_URL + "/dataset", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                Toast.makeText(getApplicationContext(), "succes", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(getApplicationContext(), "failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
