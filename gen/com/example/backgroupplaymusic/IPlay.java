/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: D:\\20140822\\BackgroupPlayMusic\\src\\com\\example\\backgroupplaymusic\\IPlay.aidl
 */
package com.example.backgroupplaymusic;
public interface IPlay extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.example.backgroupplaymusic.IPlay
{
private static final java.lang.String DESCRIPTOR = "com.example.backgroupplaymusic.IPlay";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.example.backgroupplaymusic.IPlay interface,
 * generating a proxy if needed.
 */
public static com.example.backgroupplaymusic.IPlay asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.example.backgroupplaymusic.IPlay))) {
return ((com.example.backgroupplaymusic.IPlay)iin);
}
return new com.example.backgroupplaymusic.IPlay.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_play:
{
data.enforceInterface(DESCRIPTOR);
this.play();
reply.writeNoException();
return true;
}
case TRANSACTION_pasue:
{
data.enforceInterface(DESCRIPTOR);
this.pasue();
reply.writeNoException();
return true;
}
case TRANSACTION_registerLisenery:
{
data.enforceInterface(DESCRIPTOR);
com.example.backgroupplaymusic.IPlayMusicListenery _arg0;
_arg0 = com.example.backgroupplaymusic.IPlayMusicListenery.Stub.asInterface(data.readStrongBinder());
this.registerLisenery(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_unRegisterLisenery:
{
data.enforceInterface(DESCRIPTOR);
com.example.backgroupplaymusic.IPlayMusicListenery _arg0;
_arg0 = com.example.backgroupplaymusic.IPlayMusicListenery.Stub.asInterface(data.readStrongBinder());
this.unRegisterLisenery(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.example.backgroupplaymusic.IPlay
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public void play() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_play, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void pasue() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_pasue, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void registerLisenery(com.example.backgroupplaymusic.IPlayMusicListenery listenery) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((listenery!=null))?(listenery.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerLisenery, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void unRegisterLisenery(com.example.backgroupplaymusic.IPlayMusicListenery listenery) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((listenery!=null))?(listenery.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unRegisterLisenery, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_play = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_pasue = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_registerLisenery = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_unRegisterLisenery = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
}
public void play() throws android.os.RemoteException;
public void pasue() throws android.os.RemoteException;
public void registerLisenery(com.example.backgroupplaymusic.IPlayMusicListenery listenery) throws android.os.RemoteException;
public void unRegisterLisenery(com.example.backgroupplaymusic.IPlayMusicListenery listenery) throws android.os.RemoteException;
}
