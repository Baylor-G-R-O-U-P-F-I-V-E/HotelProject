Cloning This Repository Locally
===============================

1.  [Cloning the Repository](#cloning-the-repository)
1.  [macOS](#git-macos)
    1.  [Installing Homebrew](#installing-homebrew)
    2.  [Installing `git` with Homebrew](#installing-git-with-homebrew)
1.  [Windows](#git-windows)
1.  [Linux](#git-linux)


-----


Cloning the Repository
----------------------

Once git is installed, clone this repository by first moving into a directory 
where you want to store, then running the `git clone` command.

```bash
# move to a directory where you want to store your local copy
mkdir tmp/
cd tmp/

# git clone the sucker
git clone https://github.com/Baylor-G-R-O-U-P-F-I-V-E/HotelProject.git

# move into our project
cd HotelProject
```

-----

Git, macOS
----------

You can use Homebrew (a MacOS package manager) to install git. Homebrew is
a package manager and can be used to easily install many other packages.


-----


### Installing Homebrew 

To download homebrew on macOS, run the following command:  


```bash
# install brew
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```


After that command finishes, the script will print out two more commands that 
you should run in your terminal. These commands will add homebrew to your 
system path so that you can run the packages that you install with homebrew. 
The commands will look something like the following (note that the dotfile in 
this example is `.zprofile`, but can also be `.zshrc`, `.bashrc`, or `bash_profile` 
based on what shell you are using).  


```bash
# Add the path to your corresponding dotfile.
# These instructions should also print out
echo 'eval $(/opt/homebrew/bin/brew shellenv)' >> ~/.zprofile
eval $(/opt/homebrew/bin/brew shellenv)

# Run `brew help` to make sure your paths work
brew help
```


-----


### Installing `git` with Homebrew

Now that homebrew is installed and have verified that the path works, run 
through the following commands to install and initialize git.  


```bash
# Install git
brew install git

# Check that it installed and your paths work
git --version

# Set your usename and email (these should be the same as your GitHub account)
git config --global user.name "username"
git config --global user.email "usr@email.com"

# You can also optionally set your default text editor and a color ui
git config --global core.editor vim
git config --global color.ui true
```


Note that github supplies a `noreply` email address that you can supply to 
keep your personal email private during commits.  

See this link for more details: 
[Setting Your Commit Email Address](https://docs.github.com/en/account-and-profile/setting-up-and-managing-your-personal-account-on-github/managing-email-preferences/setting-your-commit-email-address)


-----


Git, Windows
------------

Please see here <https://git-scm.com/download/win> (I'm sorry it's been a while 
since I've used Windows).


-----


Git, Linux
----------

Idk cope i guess.
