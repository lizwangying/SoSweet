package com.liz.wangying.sosweet;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.liz.wangying.sosweet.adapter.ChatAdapter;
import com.liz.wangying.sosweet.databinding.ActivityDetailBinding;
import com.liz.wangying.sosweet.model.ChatBean;
import com.liz.wangying.sosweet.view.AutoScrollTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * .,:,,,                                        .::,,,::.
 * .::::,,;;,                                  .,;;:,,....:i:
 * :i,.::::,;i:.      ....,,:::::::::,....   .;i:,.  ......;i.
 * :;..:::;::::i;,,:::;:,,,,,,,,,,..,.,,:::iri:. .,:irsr:,.;i.
 * ;;..,::::;;;;ri,,,.                    ..,,:;s1s1ssrr;,.;r,
 * :;. ,::;ii;:,     . ...................     .;iirri;;;,,;i,
 * ,i. .;ri:.   ... ............................  .,,:;:,,,;i:
 * :s,.;r:... ....................................... .::;::s;
 * ,1r::. .............,,,.,,:,,........................,;iir;
 * ,s;...........     ..::.,;:,,.          ...............,;1s
 * :i,..,.              .,:,,::,.          .......... .......;1,
 * ir,....:rrssr;:,       ,,.,::.     .r5S9989398G95hr;. ....,.:s,
 * ;r,..,s9855513XHAG3i   .,,,,,,,.  ,S931,.,,.;s;s&BHHA8s.,..,..:r:
 * :r;..rGGh,  :SAG;;G@BS:.,,,,,,,,,.r83:      hHH1sXMBHHHM3..,,,,.ir.
 * ,si,.1GS,   sBMAAX&MBMB5,,,,,,:,,.:&8       3@HXHBMBHBBH#X,.,,,,,,rr
 * ;1:,,SH:   .A@&&B#&8H#BS,,,,,,,,,.,5XS,     3@MHABM&59M#As..,,,,:,is,
 * .rr,,,;9&1   hBHHBB&8AMGr,,,,,,,,,,,:h&&9s;   r9&BMHBHMB9:  . .,,,,;ri.
 * :1:....:5XSi;r8BMBHHA9r:,......,,,,:ii19GG88899XHHH&GSr.      ...,:rs.
 * ;s.     .:sS8G8GG889hi.        ....,,:;:,.:irssrriii:,.        ...,,i1,
 * ;1,         ..,....,,isssi;,        .,,.                      ....,.i1,
 * ;h:               i9HHBMBBHAX9:         .                     ...,,,rs,
 * ,1i..            :A#MBBBBMHB##s                             ....,,,;si.
 * .r1,..        ,..;3BMBBBHBB#Bh.     ..                    ....,,,,,i1;
 * :h;..       .,..;,1XBMMMMBXs,.,, .. :: ,.               ....,,,,,,ss.
 * ih: ..    .;;;, ;;:s58A3i,..    ,. ,.:,,.             ...,,,,,:,s1,
 * .s1,....   .,;sh,  ,iSAXs;.    ,.  ,,.i85            ...,,,,,,:i1;
 * .rh: ...     rXG9XBBM#M#MHAX3hss13&&HHXr         .....,,,,,,,ih;
 * .s5: .....    i598X&&A&AAAAAA&XG851r:       ........,,,,:,,sh;
 * . ihr, ...  .         ..                    ........,,,,,;11:.
 * ,s1i. ...  ..,,,..,,,.,,.,,.,..       ........,,.,,.;s5i.
 * .:s1r,......................       ..............;shs,
 * . .:shr:.  ....                 ..............,ishs.
 * .,issr;,... ...........................,is1s;.
 * .,is1si;:,....................,:;ir1sr;,
 * ..:isssssrrii;::::::;;iirsssssr;:..
 * .,::iiirsssssssssrri;;:.
 */
public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        AutoScrollTextView autoScrollTextView;
        List<ChatBean> list = new ArrayList<>();
        ChatBean chat = new ChatBean();
        chat.setMsg("哈哈，第一次聊天");
        chat.setDate("17:18");
        chat.setReceive(true);
        ChatBean chat2 = new ChatBean();
        chat2.setMsg("Hi");
        chat2.setDate("17:19");
        chat2.setReceive(false);

        list.add(chat);
        list.add(chat2);
        binding.recyclerViewChat.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        binding.recyclerViewChat.setAdapter(new ChatAdapter(DetailActivity.this,list));


//        autoScrollTextView = (AutoScrollTextView)findViewById(R.id.TextViewNotice);
//        autoScrollTextView.init(getWindowManager());
//        autoScrollTextView.startScroll();
    }


}
