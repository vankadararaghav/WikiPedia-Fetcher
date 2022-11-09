package tech.codingclub.helix.entity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;



public class WikiPediaFetcher  {
    private String  keyWord;

    public  WikiPediaFetcher(String keyWord)
    {
        this.keyWord = keyWord;
    }
    public WikiResult getResult() {
        String response="";
        if (keyWord == "" || keyWord.length() == 0)
            return null;
        this.keyWord = this.keyWord.trim().replaceAll(" ", "_");
        String url = "https://en.wikipedia.org/wiki/" + this.keyWord;
        // String url = "https://en.wikipedia.org/wiki/Albert_Einstein";
        String wikiresponse;
        try {
            wikiresponse = HttpURLConnectionExample.sendGet(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Document document = Jsoup.parse(wikiresponse);

        Elements childElements=  document.body().select(".mw-parser-output > *");
        int state=0;
        for(Element childElement : childElements)
        {
            if(state==0)
            {
                if(childElement.tagName().equals("table"))
                {
                    state=1;
                }
            }
            else if(state==1)
            {
                if(childElement.tagName().equals("p"))
                {
                    state=2;
                    response = childElement.text();
                }
            }
        }
        //System.out.println(response);
        String imgUrl = document.body().select(".infobox img").get(0).attr("src");
       return new  WikiResult(imgUrl,response);
    }

}

