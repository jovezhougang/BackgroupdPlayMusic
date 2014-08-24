package com.example.backgroupplaymusic;
import com.example.backgroupplaymusic.IPlayMusicListenery;
interface IPlay{
    void play();
    void pasue();
    void registerLisenery(IPlayMusicListenery listenery);
    void unRegisterLisenery(IPlayMusicListenery listenery);
}