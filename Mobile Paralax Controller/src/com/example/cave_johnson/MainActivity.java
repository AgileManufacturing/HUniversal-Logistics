package com.example.cave_johnson;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ftdi.j2xx.D2xxManager;
import com.ftdi.j2xx.FT_Device;

public class MainActivity extends Activity implements View.OnClickListener {
	
    private final static String TAG = "FPGA_FIFO Activity";

    private static D2xxManager ftD2xx = null;
    private FT_Device ftDev;
    
    private Button enable;
    private Button move;
    private Button disable;
    
    static final int READBUF_SIZE  = 256;
    byte[] rbuf  = new byte[READBUF_SIZE];
    char[] rchar = new char[READBUF_SIZE];
    int mReadSize=0;

    boolean mThreadIsStopped = true;
    Handler mHandler = new Handler();
    Thread mThread;
    int count = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        enable = (Button)findViewById(R.id.Enable);
        enable.setOnClickListener(this);
        
        move = (Button)findViewById(R.id.Move);
        move.setOnClickListener(this);
        
        disable = (Button)findViewById(R.id.Disable);
        disable.setOnClickListener(this);
        
        try {
            ftD2xx = D2xxManager.getInstance(this);
        } catch (D2xxManager.D2xxException ex) {
            Log.e(TAG,ex.toString());
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
        registerReceiver(mUsbReceiver, filter);
    }
    
	@Override
	public void onClick(View v) {
		
		switch(v.getId()){
			case R.id.Enable:
				WriteString("$1");
				break;
			case R.id.Move:
				WriteString("$2,9,9");
				break;
			case R.id.Disable:
				WriteString("$0");
				break;
		}
		


	}
	
	public void WriteString(String input){
		openDevice();
		SetConfig(115200, (byte)8, (byte)1, (byte)0, (byte)0);
		byte[] writeByte = input.getBytes();
        ftDev.write(writeByte, input.length());
	}

    public void onDestroy() {
        super.onDestroy();
        mThreadIsStopped = true;
        unregisterReceiver(mUsbReceiver);
    }

    private void openDevice() {
        if(ftDev != null) {
            if(ftDev.isOpen()) {
                if(mThreadIsStopped) {
                    ftDev.purge((byte) (D2xxManager.FT_PURGE_TX | D2xxManager.FT_PURGE_RX));
                    ftDev.restartInTask();
                }
                return;
            }
        }

        int devCount = 0;
        devCount = ftD2xx.createDeviceInfoList(this);
        D2xxManager.FtDeviceInfoListNode[] deviceList = new D2xxManager.FtDeviceInfoListNode[devCount];
        ftD2xx.getDeviceInfoList(devCount, deviceList);

        if(devCount <= 0) {
            return;
        }

        if(ftDev == null) {
            ftDev = ftD2xx.openByIndex(this, 0);
        } else {
            synchronized (ftDev) {
                ftDev = ftD2xx.openByIndex(this, 0);
            }
        }

        if(ftDev.isOpen()) {
            if(mThreadIsStopped) {
            	SetConfig(115200, (byte)8, (byte)1, (byte)0, (byte)0);
                ftDev.purge((byte) (D2xxManager.FT_PURGE_TX | D2xxManager.FT_PURGE_RX));
                ftDev.restartInTask();
            }
        }
    }

    private void closeDevice() {
        mThreadIsStopped = true;
        if(ftDev != null) {
            ftDev.close();
        }
    }

    public void SetConfig(int baud, byte dataBits, byte stopBits, byte parity, byte flowControl) {
        if (ftDev.isOpen() == false) {
            Log.e(TAG, "SetConfig: device not open");
            return;
        }

        // configure port
        ftDev.setBitMode((byte) 0, D2xxManager.FT_BITMODE_RESET);
        ftDev.setBaudRate(baud);
        ftDev.setDataCharacteristics(D2xxManager.FT_DATA_BITS_8, D2xxManager.FT_STOP_BITS_1, D2xxManager.FT_PARITY_NONE);
        ftDev.setFlowControl(D2xxManager.FT_FLOW_NONE, (byte) 0x0b, (byte) 0x0d);
    }
	
	BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
            	openDevice();
            } else if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
                closeDevice();
            }
        }
    };

}
