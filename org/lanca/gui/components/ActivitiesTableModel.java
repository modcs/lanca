/*
**The MIT License (MIT)
**Copyright (c) <2014> <CIn-UFPE>
** 
**Permission is hereby granted, free of charge, to any person obtaining a copy
**of this software and associated documentation files (the "Software"), to deal
**in the Software without restriction, including without limitation the rights
**to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
**copies of the Software, and to permit persons to whom the Software is
**furnished to do so, subject to the following conditions:
** 
**The above copyright notice and this permission notice shall be included in
**all copies or substantial portions of the Software.
** 
**THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
**IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
**FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
**AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
**LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
**OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
**THE SOFTWARE.
*/


package org.lanca.gui.components;

import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import org.caboclo.activities.Activity;
import org.caboclo.activities.ActivityListener;


public class ActivitiesTableModel implements ActivityListener {

    private Map<String, Integer> idToRowN;
    private DefaultTableModel model;

    public ActivitiesTableModel(DefaultTableModel model) {
        idToRowN = new HashMap<>();
        this.model = model;
    }

    public void addActivity(Activity act) {
        int row = model.getRowCount();

        model.insertRow(row, new Object[]{act.getId(),
            act.getStatus().toString(), act.getDescription(), act.getDate()});

        idToRowN.put(act.getId(), row);
        
        act.addListener(this);
    }

    @Override
    public void activityStarted(Activity act) {
        updateActivity(act);
    }

    @Override
    public void activityCanceled(Activity act) {
        updateActivity(act);
    }

    @Override
    public void activityResumed(Activity act) {
        updateActivity(act);
    }

    @Override
    public void activityFinished(Activity act) {
        updateActivity(act);
    }

    @Override
    public void activitySuspended(Activity act) {
        updateActivity(act);
    }

    private void updateActivity(Activity act) {
        int row = idToRowN.get(act.getId());

        model.setValueAt(act.getStatus().toString(), row, 1);
    }

    @Override
    public void activityFailed(Activity act) {
        updateActivity(act);
    }
   
}
