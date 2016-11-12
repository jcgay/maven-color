# Windows Box

## Usage

You will need [`vagrant`](https://www.vagrantup.com) to manage the environment.

    vagrant up
    
will start a VM in GUI mode.  

`maven-color` is mount at `C:\maven-color`.  

## Construction

The base box is a Windows 2012 Server edition because Windows 8.x client does not have WinRM activated by default and I don't find one correctly packaged (using ssh or WinRM) on [vagrantcloud](https://vagrantcloud.com/boxes) or [vagrantbox.es](http://www.vagrantbox.es).  

Chocolatey bootstrap script comes from: [Provisioning a windows box with vagrant and chocolatey](http://www.tzehon.com/2014/01/20/provisioning-a-windows-box-with-vagrant-chocolatey-and-puppet-part-1/).  
