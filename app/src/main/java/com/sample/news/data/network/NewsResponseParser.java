package com.sample.news.data.network;

import com.sample.news.data.models.MediaEntity;
import com.sample.news.data.models.NewsEntity;
import com.sample.news.data.models.NewsResponse;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for parsing the response received from News Api.
 * NOTE - JSON response from the webservice has an issue , in case of
 * blank array, it sends "" (treated as JsonObject) instead of [].
 * Standard JSON/GSON parsers are unable to handle this scenario and throw
 * exception. This class has been written to handle this errorneous case.
 * TODO - response should be rectified from webservice side
 */
public class NewsResponseParser extends TypeAdapter<NewsResponse> {
    @Override
    public void write(JsonWriter out, NewsResponse value) throws IOException {
    }

    @Override
    public NewsResponse read(JsonReader in) throws IOException {
        NewsResponse newsResponse = new NewsResponse();
        List<NewsEntity> newsItems = new ArrayList<>();
        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "status":
                    newsResponse.setStatus(in.nextString());
                    break;
                case "copyright":
                    newsResponse.setCopyright(in.nextString());
                    break;
                case "num_results":
                    newsResponse.setNumResults(Integer.parseInt(in.nextString()));
                    break;
                case "results":
                    in.beginArray();
                    while (in.hasNext()) {
                        final NewsEntity newsItem = new NewsEntity();
                        in.beginObject();
                        while (in.hasNext()) {
                            switch (in.nextName()) {
                                case "title":
                                    newsItem.setTitle(in.nextString());
                                    break;
                                case "url":
                                    newsItem.setUrl(in.nextString());
                                    break;
                                case "abstract":
                                    newsItem.setAbstract(in.nextString());
                                    break;
                                case "multimedia":
                                    if (!in.peek().equals(JsonToken.BEGIN_ARRAY)) {
                                        in.skipValue();
                                        break;
                                    }
                                    in.beginArray();
                                    final ArrayList<MediaEntity> mediaList = new ArrayList<>();
                                    while (in.hasNext()) {
                                        in.beginObject();
                                        final MediaEntity media = new MediaEntity();
                                        while (in.hasNext()) {
                                            switch (in.nextName()) {
                                                case "url":
                                                    media.setUrl(in.nextString());
                                                    break;
                                                case "format":
                                                    media.setFormat(in.nextString());
                                                    break;
                                                default:
                                                    in.skipValue();
                                                    break;
                                            }
                                        }
                                        mediaList.add(media);
                                        in.endObject();
                                    }
                                    newsItem.setMultimedia(mediaList);
                                    in.endArray();
                                    break;

                                default:
                                    in.skipValue();
                                    break;
                            }
                        }
                        in.endObject();
                        newsItems.add(newsItem);
                        newsResponse.setResults(newsItems);
                    }
                    in.endArray();
                    break;
                default:
                    in.skipValue();
                    break;

            }

        }
        in.endObject();

        return newsResponse;
    }
}
