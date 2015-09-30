Fragment-Back-Stack
===================

Fragment Back Stack manager while displaying fragments on single activity and need to maintain on back press

Purpose
-------------

We know that there is activity stack in Android. We don't need to maintain the stack while opening or closing
activity. It will automatically handle the stack and show you the top of activity when you pressed `back` button.

But in fragment, its neccessary to handle them. Because Android is not going to handle them. We need to create
a stack of fragment and manage them while pressing `back` button.

So, I have created one demo to represent how to handle the fragment in Back Stack.

Usage
-------------

In the sample application, you will find one object named `fragmentStack`. Its a `Stack` which will
push and pop the fragment as per requirement.

Whenever you are displaying any new fragment, just push that fragment into stack using following code.

    //here this fragment is our first fragment
    homeListFragment = new HomeListFragment();
    fragmentStack.push(homeListFragment);
    
![First Fragment](FirstFragment.png)
    
And when you are displaying any other fragment over this fragment, use following code.

We will create a new object of second fragment and add it to stack.

    //here this fragment is second fragment
    resultListFragment = new ResultListFragment();
    //hide the last fragment
    ft.hide(fragmentStack.lastElement());
    //push the new fragment into stack
    fragmentStack.push(resultListFragment);
		
![Second Fragment](Second Fragment.png)
	
When `backPressed` event fires, we will check whether stack size is `2` or not. If it is, then we will pop last 
fragment and display the previous fragment by following code.

    if (fragmentStack.size() == 2) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        fragmentStack.lastElement().onPause();
        ft.remove(fragmentStack.pop());
        fragmentStack.lastElement().onResume();
        ft.show(fragmentStack.lastElement());
        ft.commit();
    } else {
        //if size is `1` it means first fragment is visible and we can exit from application
        super.onBackPressed();
    }
    
License
-------------

Copyright 2014 Chintan Rathod

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
