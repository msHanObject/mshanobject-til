package cz.vancura.searchapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import util.JsonExtraction;

public class MainActivity extends Activity {
	//
	private JsonExtraction jsonExtraction;
	private List<String> tagList;

	public MainActivity() {
	    //
        jsonExtraction = new JsonExtraction();
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final EditText keywordEdit, tagEdit;
		Button searchBtn;
		final TextView result;

		Log.d("search", "**** APP START");

		keywordEdit = (EditText) findViewById(R.id.keywordEdit);
		tagEdit = (EditText) findViewById(R.id.tagEdit);
		searchBtn = (Button) findViewById(R.id.searchBtn);
		result = (TextView) findViewById(R.id.jsonText);

		searchBtn.setOnClickListener(new OnClickListener() {
			 public void onClick(View v) {

				final String keywordStr = keywordEdit.getText().toString();
				String tagStr = tagEdit.getText().toString();

				Log.d("serach", "Searching for : " + keywordStr + "By " + tagStr);
				result.setText("Searching for : " + keywordStr + " is analyzed by " + tagStr);

				tagList = convertToList(tagStr);
				Log.d("tagList", tagList.toString());

				Thread thread = new Thread(new Runnable()
				{
					@Override
					public void run()
					{

							try {


									// looking for
									String strNoSpaces = keywordStr.replace(" ", "+");

									// Your API key
									String key="AIzaSyAwYD3u_4hfm5sA8-pPwElAbknBKflVDiM";

									// Your Search Engine ID
									String cx = "004387748270250669872:8t1n_mkuj9y";

									String urlStr = "https://www.googleapis.com/customsearch/v1?q=" + strNoSpaces + "&key=" + key + "&cx=" + cx + "&alt=json";
									Log.d("search", "Url = "+  urlStr);

									// Get json
									String jsonResult = httpGet(urlStr);
									result.setText(jsonResult);

								}
								catch(Exception e) {
									System.out.println("Error1 " + e.getMessage());
								}

							 }


					private String httpGet(String urlStr) throws IOException {

						URL url = new URL(urlStr);

						HttpURLConnection conn = (HttpURLConnection) url.openConnection();

						if(conn.getResponseCode() != 200) {
							throw new IOException(conn.getResponseMessage());
						}

						Log.d("search", "Connection status = " + conn.getResponseMessage());

						BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
						StringBuilder builder = new StringBuilder();
						String line;

						while((line = reader.readLine()) != null) {
							Log.d("search", "Line =" + reader.readLine());
							builder.append(jsonExtraction.getTag(line, tagList));

						}
						reader.close();

						conn.disconnect();
						return builder.toString();
					}
				});

				thread.start();

			 }
		});
	}

	private List<String> convertToList(String str) {
	    //
        String[] tags = str.split("\\s");
        List<String> list = new ArrayList<>();

        for (String tag : tags) {
            list.add(tag);
        }

        return list;
    }
}