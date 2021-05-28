package com.exercice.application

import com.exercice.domain.{Entities, HashTags, Tweet, User}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class TweetServiceTest extends AnyFlatSpec with Matchers{

  behavior of "TweetServiceTest"

  it should "toTweet" in {
    val service =new TweetService(null)
    val tweetString ="{\"created_at\":\"Tue Jun 12 16:10:54 +0000 2018\",\"id\":1006569542993227800,\"id_str\":\"1006569542993227777\",\"text\":\"Dandooo fechas asi me gusta #NintendoE3\",\"source\":\"<a href=\\\"http://twitter.com/download/iphone\\\" rel=\\\"nofollow\\\">Twitter for iPhone</a>\",\"truncated\":false,\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":185203217,\"id_str\":\"185203217\",\"name\":\"Lilicia\",\"screen_name\":\"Lilicia_Onechan\",\"location\":\"Madrid / España\",\"url\":\"http://lilicia-onechan.deviantart.com/\",\"description\":\"♀Lvl27.Dibujando y escuchando música soy feliz. La niña pegada a una consola.Demasiado optimista. ShikaTema/Gintama/Zelda/FMA/Dragon Age/Linkin Park/Geralt/FF\",\"translator_type\":\"none\",\"protected\":false,\"verified\":false,\"followers_count\":360,\"friends_count\":422,\"listed_count\":19,\"favourites_count\":3736,\"statuses_count\":57803,\"created_at\":\"Tue Aug 31 13:11:31 +0000 2010\",\"utc_offset\":null,\"time_zone\":null,\"geo_enabled\":true,\"lang\":\"es\",\"contributors_enabled\":false,\"is_translator\":false,\"profile_background_color\":\"000000\",\"profile_background_image_url\":\"http://abs.twimg.com/images/themes/theme16/bg.gif\",\"profile_background_image_url_https\":\"https://abs.twimg.com/images/themes/theme16/bg.gif\",\"profile_background_tile\":false,\"profile_link_color\":\"1B95E0\",\"profile_sidebar_border_color\":\"000000\",\"profile_sidebar_fill_color\":\"000000\",\"profile_text_color\":\"000000\",\"profile_use_background_image\":false,\"profile_image_url\":\"http://pbs.twimg.com/profile_images/1005911165761937414/tkhnL68Q_normal.jpg\",\"profile_image_url_https\":\"https://pbs.twimg.com/profile_images/1005911165761937414/tkhnL68Q_normal.jpg\",\"profile_banner_url\":\"https://pbs.twimg.com/profile_banners/185203217/1504735283\",\"default_profile\":false,\"default_profile_image\":false,\"following\":null,\"follow_request_sent\":null,\"notifications\":null},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"is_quote_status\":false,\"quote_count\":0,\"reply_count\":0,\"retweet_count\":0,\"favorite_count\":76,\"entities\":{\"hashtags\":[{\"text\":\"NintendoE3\",\"indices\":[28,39]}],\"urls\":[],\"user_mentions\":[],\"symbols\":[]},\"favorited\":false,\"retweeted\":false,\"filter_level\":\"low\",\"lang\":\"es\",\"timestamp_ms\":\"1528819854864\"}"

    val t = service.toTweet(tweetString)

    t should be (Some(Tweet(User(185203217,Some("Lilicia_Onechan")),Entities(List(),List(HashTags("NintendoE3"))),76)))
  }

}
