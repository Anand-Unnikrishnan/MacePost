package com.macepost.feeds;

public class TweetData {
	public int tweet_id,user_id,likes;
	public String tweet,datetime;
	public void setdata(int tweet_id,int user_id,int likes,String tweet,String datetime){
		this.tweet_id=tweet_id;
		this.user_id = user_id;
		this.likes = likes;
		this.tweet = tweet;
		this.datetime = datetime;
		//String s = tweet_id+" "+user_id+" "+likes+" "+tweet+" "+datetime;
		//return(s);
	}
}