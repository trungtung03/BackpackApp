package com.example.backpackapp.nhap;

import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

public class nhap extends AppCompatActivity {
//    FragmentManager fr = getSupportFragmentManager();
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        RecyclerView recyclerView = null;
//        recyclerView.setFocusable(false);
//    }
    //    val photoGuideAdapter = activity?.let { PhotoGuideAdapter(it, getListPhoto()) }
//    view_pager.adapter = photoGuideAdapter
//        circle_indicator.setViewPager(view_pager)
//    photoGuideAdapter?.registerDataSetObserver(circle_indicator.dataSetObserver)
//
//    private fun getListPhoto(): List<PhotoGuide> {
//        var list: ArrayList<PhotoGuide> = ArrayList()
//        list.add(PhotoGuide(R.drawable.success_blue))
//        list.add(PhotoGuide(R.drawable.background_backpack_splash))
//        list.add(PhotoGuide(R.drawable.ellipse_backpack_splash_1_yellow))
//        list.add(PhotoGuide(R.drawable.avatar_comback_backpack))
//        return list
//    }


//    class PhotoGuideAdapter(var mContext: Context, var guideList: List<PhotoGuide>) :
//    PagerAdapter() {
//
//        override fun instantiateItem(container: ViewGroup, position: Int): Any {
//            val view = LayoutInflater.from(container.context).inflate(R.layout.fragment_guide_03, container, false)
//            val photoGuide = guideList[position]
//            Glide.with(mContext).load(photoGuide.resourceId).into(view.frag)
//            container.addView(view)
//            return view
//        }
//
//        override fun getCount(): Int {
//            if (guideList.isNotEmpty()) {
//                return guideList.size
//            }
//            return 0
//        }
//
//        override fun isViewFromObject(view: View, `object`: Any): Boolean {
//            return view == `object`
//        }
//
//        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//            container.removeView(`object` as View?)
//        }
//    }

//    class PhotoGuide(var resourceId: Int?) : Serializable {
//
//    }
}
