package com.pristinebs.searchimageyourimage;

import java.util.ArrayList;

public class ImgSearchModel {


    public SearchMetadata search_metadata;
    public SearchParameters search_parameters;
    public ArrayList<KnowledgeGraph> knowledge_graph;
    public ArrayList<VisualMatch> visual_matches;
    public ReverseImageSearch reverse_image_search;



    public class SearchMetadata{
        public String id;
        public String status;
        public String json_endpoint;
        public String created_at;
        public String processed_at;
        public String google_lens_url;
        public String raw_html_file;
        public String prettify_html_file;
        public double total_time_taken;
    }

    public class SearchParameters{
        public String engine;
        public String url;
    }

    public class Size{
        public int width;
        public int height;
    }

    public class VisualMatch{
        public int position;
        public String title;
        public String link;
        public String source;
        public String source_icon;
        public String thumbnail;
    }

    public class Image{
        public String title;
        public String source;
        public String link;
        public Size size;
    }

    public class KnowledgeGraph{
        public String title;
        public String subtitle;
        public String link;
        public MoreImages more_images;
        public String thumbnail;
        public ArrayList<Image> images;
    }

    public class MoreImages{
        public String link;
        public String serpapi_link;
    }

    public class ReverseImageSearch{
        public String link;
    }

}
