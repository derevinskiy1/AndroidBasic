package me.icxd.bookshelve.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import org.litepal.crud.DataSupport;

import cn.pedant.SweetAlert.SweetAlertDialog;
import me.icxd.bookshelve.R;
import me.icxd.bookshelve.activity.BookInfoActivity;
import me.icxd.bookshelve.adapter.BookGridAdapter;
import me.icxd.bookshelve.model.bean.Book;

/**
 * Created by HaPBoy on 5/18/16.
 */
public class BookGridFragment extends Fragment implements AdapterView.OnItemClickListener {

    public static final int TYPE_ALL = 1;
    public static final int TYPE_FAVORITE = 2;

    private static final String ARG_TYPE = "type";
    private int type = TYPE_ALL; // 显示的数据（全部、收藏）
    
    private GridView gridView; // 网格列表
    private BookGridAdapter bookGridAdapter; // 数据适配器
    private int gridPosition = -1; // 选中项的position

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (this.isVisible()) {
            if (isVisibleToUser) {
                fetchData();
                bookGridAdapter.notifyDataSetChanged();
            }
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    public static BookGridFragment newInstance(int type) {
        BookGridFragment fragment = new BookGridFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt(ARG_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_grid, container, false);

        // gridView
        gridView = (GridView) view.findViewById(R.id.gridView);

        // ItemClickListener
        gridView.setOnItemClickListener(this);

        // ContextMenu - 'Delete' Function
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                gridPosition = position;
                Log.i("HB", "onItemLongClick:gridPosition: " + gridPosition);
                return false;
            }
        });
        registerForContextMenu(gridView);

        // EmptyView
        View emptyView = view.findViewById(R.id.empty);
        ImageView ivIcon = (ImageView) emptyView.findViewById(R.id.iv_icon);
        TextView tvText = (TextView) emptyView.findViewById(R.id.tv_text);
        if (type == TYPE_FAVORITE) {
            ivIcon.setImageDrawable(new IconicsDrawable(getContext()).icon(GoogleMaterial.Icon.gmd_favorite).colorRes(R.color.grid_empty_icon).sizeDp(40));
            tvText.setText("暂无收藏");
        } else {
            ivIcon.setImageDrawable(new IconicsDrawable(getContext()).icon(GoogleMaterial.Icon.gmd_import_contacts).colorRes(R.color.grid_empty_icon).sizeDp(48));
            tvText.setText("暂无图书");
        }
        gridView.setEmptyView(emptyView);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        bookGridAdapter = new BookGridAdapter(getContext());
        fetchData();
        bookGridAdapter.notifyDataSetChanged();
        gridView.setAdapter(bookGridAdapter);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), BookInfoActivity.class);
        intent.putExtra("id", (int) bookGridAdapter.getItemId(position));
        startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(1, 1, 1, "删除选中");
        menu.add(1, 2, 1, "删除全部");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Log.i("HB", "onContextItemSelected:adapter.getCount(): " + bookGridAdapter.getCount());
        Log.i("HB", "onContextItemSelected:gridPosition: " + gridPosition);
        if (item.getItemId() == 1 && gridPosition != -1) {
            // 删除选中
            new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("确定删除这本图书吗")
                    .setContentText("删除后将无法恢复。")
                    .setConfirmText("是的")
                    .setCancelText("取消")
                    .showCancelButton(true)
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            // 刷新数据
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    DataSupport.delete(Book.class, bookGridAdapter.getItemId(gridPosition));
                                    fetchData();
                                    bookGridAdapter.notifyDataSetChanged();
                                }
                            }, 800);

                            sDialog
                                    .setTitleText("删除成功")
                                    .setContentText("该本图书已被成功删除。")
                                    .setConfirmText("确定")
                                    .showCancelButton(false)
                                    .setConfirmClickListener(null)
                                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        }
                    })
                    .show();
        } else if (item.getItemId() == 2) {
            // 删除全部
            new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("确定删除全部图书吗")
                    .setContentText("删除后将无法恢复。")
                    .setConfirmText("是的")
                    .setCancelText("取消")
                    .showCancelButton(true)
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            // 刷新数据
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    DataSupport.deleteAll(Book.class);
                                    fetchData();
                                    bookGridAdapter.notifyDataSetChanged();
                                }
                            }, 1000);
                            sDialog
                                    .setTitleText("删除成功")
                                    .setContentText("全部图书已被成功删除。")
                                    .setConfirmText("确定")
                                    .showCancelButton(false)
                                    .setConfirmClickListener(null)
                                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        }
                    })
                    .show();
        } else {
            return super.onContextItemSelected(item);
        }
        return true;
    }

    public void fetchData() {
        Log.i("HB", type + "GridFragment.fetchData");
        if (type == TYPE_FAVORITE) {
            bookGridAdapter.setData(DataSupport.where("favourite = ?", "1").order("id desc").find(Book.class));
        } else {
            bookGridAdapter.setData(DataSupport.order("id desc").find(Book.class));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("HB", type + "GridFragment.onResume");
        fetchData();
        bookGridAdapter.notifyDataSetChanged();
    }
}
