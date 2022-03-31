package com.android.medicareapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.android.medicareapp.Adapters.chat.customeroperation.BookingModel;
import com.android.medicareapp.Adapters.chat.customeroperation.CustomerOpsAdapter;
import com.android.medicareapp.Adapters.chat.customeroperation.MessageModel;
import com.android.medicareapp.Adapters.chat.customeroperation.OperationModel;
import com.android.medicareapp.Adapters.chat.customeroperation.OrderModel;
import com.android.medicareapp.R;
import com.android.medicareapp.Utils.Constants;
import com.android.medicareapp.databinding.ActivityCustomerOperationsBinding;

import java.util.ArrayList;
import java.util.List;

public class CustomerOperations extends AppCompatActivity implements View.OnClickListener {
    ActivityCustomerOperationsBinding binding;
    String title = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerOperationsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tool.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        binding.newMsgBtn.setOnClickListener(this);
        binding.sendMsgBtn.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        switch (getIntent().getStringExtra(Constants.operation))
        {
            case "ORDER":
                getOrders();
                break;
            case "MESSAGE":
                getMsgs();
                break;
            case "BOOKING":
                getBookings();
                break;
        }
    }
    void getOrders()
    {
        title = "Orders";
        binding.tool.toolbarTitle.setText(title);
        List<OperationModel> list = new ArrayList<>();
        list.add(new OperationModel(new OrderModel("name","dd/mm/yyyy")));
        list.add(new OperationModel(new OrderModel("name","dd/mm/yyyy")));
        list.add(new OperationModel(new OrderModel("name","dd/mm/yyyy")));
        list.add(new OperationModel(new OrderModel("name","dd/mm/yyyy")));
        binding.rvOps.setAdapter(new CustomerOpsAdapter(CustomerOperations.this, list));
    }
    void getMsgs()
    {
        title = "Messages";
        binding.tool.toolbarTitle.setText(title);
        binding.newMsgBtn.setVisibility(View.VISIBLE);
        List<OperationModel> list = new ArrayList<>();
        list.add(new OperationModel(new MessageModel("name")));
        list.add(new OperationModel(new MessageModel("name")));
        list.add(new OperationModel(new MessageModel("name")));
        list.add(new OperationModel(new MessageModel("name")));
        list.add(new OperationModel(new MessageModel("name")));
        binding.rvOps.setAdapter(new CustomerOpsAdapter(CustomerOperations.this, list));
    }
    void getBookings()
    {
        title = "Bookings";
        binding.tool.toolbarTitle.setText(title);
        List<OperationModel> list = new ArrayList<>();
        list.add(new OperationModel(new BookingModel("type","name","dd/mm/yyyy","12:00")));
        list.add(new OperationModel(new BookingModel("type","name","dd/mm/yyyy","12:00")));
        list.add(new OperationModel(new BookingModel("type","name","dd/mm/yyyy","12:00")));
        list.add(new OperationModel(new BookingModel("type","name","dd/mm/yyyy","12:00")));
        list.add(new OperationModel(new BookingModel("type","name","dd/mm/yyyy","12:00")));
        list.add(new OperationModel(new BookingModel("type","name","dd/mm/yyyy","12:00")));
        list.add(new OperationModel(new BookingModel("type","name","dd/mm/yyyy","12:00")));
        binding.rvOps.setAdapter(new CustomerOpsAdapter(CustomerOperations.this, list));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.newMsgBtn:
                binding.tool.toolbarTitle.setText("New Message");
                binding.newMsgView.setVisibility(View.VISIBLE);
                break;
            case R.id.sendMsgBtn:
                binding.tool.toolbarTitle.setText(title);
                binding.newMsgView.setVisibility(View.GONE);
                break;
        }
    }

    class MessageModeldd {
        String name;
        public MessageModeldd(String name) {
            this.name = name;
        }
    }

    @Override
    public void onBackPressed() {
        if(binding.newMsgView.getVisibility()==View.VISIBLE) {
            binding.newMsgView.setVisibility(View.GONE);
            binding.tool.toolbarTitle.setText(title);
        }
        else
        super.onBackPressed();
    }
}