package com.crio.shorturl;

import java.util.HashMap;
import java.util.Map;

public class XUrlImpl implements XUrl{

  private Map<String,String> longToShortMap;
  private Map<String,String> shortToLongMap;
  private Map<String,Integer> hitCountMap;
  


  

    public XUrlImpl(){
        longToShortMap =new HashMap<>();
        hitCountMap =new HashMap<>();
        shortToLongMap =new HashMap<>();
    }
    
    @Override
    public String registerNewUrl(String longUrl){
        if (longToShortMap.containsKey(longUrl)) {
            return longToShortMap.get(longUrl);
        }

        String shortUrl = generateShortUrl();
        longToShortMap.put(longUrl, shortUrl);
        hitCountMap.put(longUrl, 0);
        return shortUrl;
    }

    @Override
    public String registerNewUrl(String longUrl ,String shortUrl){
        if (longToShortMap.containsValue(shortUrl)) {
            System.out.println("Short URL already exists");
            return null;
        }

        longToShortMap.put(longUrl, shortUrl);
        hitCountMap.put(longUrl, 0);
        return shortUrl;
        
    }
    @Override
    public String getUrl(String shortUrl){
        if(longToShortMap.containsValue(shortUrl)){
            for(String longUrl : longToShortMap.keySet()){
                if(longToShortMap.get(longUrl).equals(shortUrl)){
                    hitCountMap.put(longUrl, hitCountMap.getOrDefault(longUrl, 0 )+1);
                    return longUrl;

                }
            }
        }
        // String longUrl = longToShortMap.get(shortUrl);
        // if (longUrl != null) {
        //     hitCountMap.put(longUrl, hitCountMap.get(longUrl) + 1);
        // }
        // return longUrl;
        return null;
    }
    
    @Override
    public Integer getHitCount(String longUrl){
        return hitCountMap.getOrDefault(longUrl, 0);
        
    }
    @Override
    public String delete(String longUrl){
        String shortUrl = longToShortMap.remove(longUrl);
        hitCountMap.remove(longUrl);
        return shortUrl;
        
    }

    private String generateShortUrl() {
        int length = 9;
        return "http://short.url/" + generateRandomAlphanumericString(length);
    }

    private String generateRandomAlphanumericString(int length) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

}

