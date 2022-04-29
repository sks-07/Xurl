package com.crio.shorturl;
import java.util.HashMap;
public class XUrlImpl implements XUrl{
    private HashMap<String, String> mUrl=new HashMap<>();
    private HashMap<String, String> rUrl=new HashMap<>();
    private HashMap<String, Integer> count=new HashMap<>();

    /*public void setUrl(){
        this.mUrl=new HashMap<>();
        this.rUrl=new HashMap<>();
    }*/
    
    public String registerNewUrl(String longUrl){
        if(mUrl.containsKey(longUrl)){
            return mUrl.get(longUrl);
        }
        else{
            String shorturl=generateShortUrl(longUrl);
            //count.put(longUrl, 0);
            if(rUrl.containsKey(shorturl)){
                return rUrl.get(shorturl);
            }
            else{
                mUrl.put(longUrl,shorturl);
                rUrl.put(shorturl, longUrl);
                return shorturl;
            }   
        }
    };

    public String generateShortUrl(String longUrl){
        int n=9;
        String base_url="http://short.url/";
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";
        
        StringBuilder sb= new StringBuilder(n);

        for(int i=0;i<n;i++){
            int index=(int)(AlphaNumericString.length()*Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return base_url+sb.toString();
    }

    public String getUrl(String shortUrl){
        String lurl=rUrl.get(shortUrl);
        if(count.containsKey(lurl)){
            int val=count.get(lurl)+1;
            count.put(lurl, val);
            //System.out.println(lurl+val);
        }else{
            count.put(lurl, 1);
            //System.out.println(lurl+1);
        }
        return lurl;
    }

    public String delete(String longUrl){
        String surl=mUrl.get(longUrl);
        rUrl.remove(surl);
        mUrl.remove(longUrl);
        return surl;
    }

    
    public String registerNewUrl(String longUrl, String shortUrl) {
        if(rUrl.get(shortUrl)==null){
            mUrl.put(longUrl, shortUrl);
            rUrl.put(shortUrl,longUrl);
            //count.put(longUrl, 0);
            return shortUrl;
        }
        return null;
    }

    
    public Integer getHitCount(String longUrl) {
        if(count.containsKey(longUrl)){
            return count.get(longUrl);
        }
        return 0;
    }

    
}