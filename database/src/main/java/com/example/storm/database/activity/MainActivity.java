package com.example.storm.database.activity;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.storm.database.R;
import com.example.storm.database.bean.Book;
import com.example.storm.database.bean.Person;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.btn1)
    Button btn1;
    @InjectView(R.id.btn2)
    Button btn2;
    @InjectView(R.id.btn3)
    Button btn3;
    @InjectView(R.id.btn4)
    Button btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                save();
                break;
            case R.id.btn2:
                find();
                break;
            case R.id.btn3:
                delete();
                break;
            case R.id.btn4:
                update();
                break;
        }
    }

    private void save() {
        Person person;
        List<Person> personList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            person = new Person();
            person.setId(i);
            person.setName("李思怡");
            person.save();

            Book book = new Book();
            book.getPersonList().add(person);
            book.setId(i);
            book.setName("张坛子");
            book.setAge(i + 10);
            book.setSex("男");
            book.setHeight(1.80f);
            book.save();
            if (book.save() && person.save()) {
                Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
            }
            /**
             * 使用saveThrows()方法来存储数据，一旦存储失败就会抛出一个DataSupportException异常，
             * 我们可以通过对这个异常进行捕获来处理存储失败的情况。
             */
            //      book.saveThrows();
            book.setPersonList(personList);

        }

    }

    private void find() {
        //查询Book表中id为1的这条记录;
        Book book = DataSupport.find(Book.class, 1);
        System.out.println("-----" + book.getId());
        System.out.println("-----" + book.getName());
        System.out.println("-----" + book.getAge());
        System.out.println("-----" + book.getSex());
        //获取第一条记录
        Book book1 = DataSupport.findFirst(Book.class);
        System.out.println("----book1" + book1.getId());
        System.out.println("----book1" + book.getName());
        //获取最后一条记录
        Book book2 = DataSupport.findLast(Book.class);
        System.out.println("----book2" + book2.getId());
        long[] ids = new long[]{1, 3, 5, 7};
        List<Book> lists = DataSupport.findAll(Book.class, ids);
        for (int i = 0; i < lists.size(); i++) {
            System.out.println("----lists" + lists.get(i).getId());
            System.out.println("----lists" + lists.get(i).getAge());
            System.out.println("----lists" + lists.get(i).getName());
        }
        //连缀查询
        //根据某个条件查询
        List<Book> books = DataSupport.where("age > ?", "15").find(Book.class);  //==>select * from Book where age > 15;
        for (int i = 0; i < books.size(); i++) {
            System.out.println("-----books" + books.get(i).getId());
            System.out.println("-----books" + books.get(i).getAge());
        }
        //查询某个列的数据
        List<Book> bookList = DataSupport.select("name", "age")
                .where("age > ?", "12").find(Book.class);
        for (int i = 0; i < books.size(); i++) {
            System.out.println("-----bookList" + bookList.get(i).getName());
            System.out.println("-----bookList" + bookList.get(i).getAge());
        }

        List<Book> bookOrder = DataSupport.select("name", "age")
                .where("age > ?", "13")
                .order("age desc").find(Book.class);

        List<Book> bookLimit = DataSupport.select("name", "age")
                .where("age > ?", "12")
                .order("age desc").limit(5).find(Book.class);


        //激进查询
        Book news = DataSupport.find(Book.class, 1, true);
        //   System.out.println("----persons" + news.getName());
        List<Person> persons = news.getPersonList();           //多对一
        for (int i = 0; i < persons.size(); i++) {
            System.out.println("----persons" + news.getName());
            System.out.println("----persons" + persons.get(i).getBook().getName());
            System.out.println("----persons" + persons.get(i).getName());
        }
    }

    private void delete() {
    }

    private void update() {
        ContentValues values = new ContentValues();
        values.put("name", "张天翼");
        DataSupport.update(Book.class, values, 1);
    }


}
