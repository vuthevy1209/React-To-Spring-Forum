import React, { useState } from "react";
import PostItem from "../../molecules/PostItem/PostItem";
import InfiniteScroll from "react-infinite-scroll-component";
import { useOutletContext } from "react-router-dom";
import NewPost from "../../molecules/NewPost/NewPost";
import s from "./style.module.css";

const PostList = ({ postList }) => {
  // Access `toggleIsPostPopup` from the outlet context
  const { toggleIsPostPopup } = useOutletContext();

  // Local state for posts
  const [posts, setPosts] = useState(postList);

  const fetchMoreData = () => {
    setTimeout(() => {
      setPosts((prevPosts) => [...prevPosts, ...prevPosts.slice(0, 2)]);
    }, 1500);
  };

  return (
    <div className={`${s.container} post_list`} id="post_list">
      <NewPost toggleIsPostPopup={toggleIsPostPopup} />

      <InfiniteScroll
        dataLength={posts.length}
        next={fetchMoreData}
        hasMore={true}
        loader={<h4>Loading...</h4>}
        endMessage={
          <p style={{ textAlign: "center" }}>
            <b>Yay! You have seen it all</b>
          </p>
        }
        scrollableTarget="post_list"
      >
        {posts.map((post, index) => (
          <PostItem key={post.id || index} post={post} />
        ))}
      </InfiniteScroll>
    </div>
  );
};

export default PostList;
