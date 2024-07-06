package com.example.finance.utils

object constants{
    const val BASE_URL= "https://newsapi.org/v2/"
    const val BASE_URL2 = "https://api.openai.com/"
    const val apiKey="c668596aac6c49c2bc30d3f4287b7360"
    const val gptapiKey="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"
    const val prompt="\"Given the above financial news article,\n"
         const val promptend=  "Please analyze the content and provide insights on how this news is likely to affect the stock market. Consider factors such as potential market reactions, impacts on specific industries or sectors, and any relevant historical patterns. Additionally, explain the role this news might play in influencing investor sentiment and market trends. Provide a comprehensive analysis with actionable insights for investors.\n"
}
//https://newsapi.org/v2/everything?q=bitcoin&apiKey=c668596aac6c49c2bc30d3f4287b7360
//TARGET LINK
//https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=c668596aac6c49c2bc30d3f4287b7360