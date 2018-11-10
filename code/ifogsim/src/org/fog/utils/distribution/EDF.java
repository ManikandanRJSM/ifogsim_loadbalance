/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fog.utils.distribution;

import java.util.Date;
import gridsim.GridSim;
import java.util.Collections;


/**
 * Class EDF<p>
 * Implements EDF (Earliest Deadline First) algorithm.
 * @author       Dalibor Klusacek
 */

public class EDF {

    private final Scheduler scheduler;

    public EDF(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void addNewJob(GridletInfo gi) {
        double runtime1 = new Date().getTime();
        Scheduler.queue.addLast(gi);
     //   Collections sort = Collections.sort(Scheduler.queue, new DeadlineComparator());
        //System.out.println(GridSim.clock()+": FCFS job received");
      //  Scheduler.runtime += (new Date().getTime() - runtime1);
        //System.out.println("New job has been received by EDF");
    }

    public int selectJob() {
        //System.out.println("Selecting job by EDF...");
        int scheduled = 0;
        ResourceInfo r_cand = null;
        for (int i = 0; i < Scheduler.queue.size(); i++) {
            GridletInfo gi = null;
        //    GridletInfo gi = (GridletInfo) Scheduler.queue.get(i);
            for (int j = 0; j < Scheduler.resourceInfoList.size(); j++) {
                ResourceInfo ri = null;
         //       ResourceInfo ri = (ResourceInfo) Scheduler.resourceInfoList.get(j);

                if (Scheduler.isSuitable(ri, gi)  && ri.canExecuteNow(gi)) {

                    r_cand = ri;
                    break;
                }
            }
            if (r_cand != null) {
   //             gi = (GridletInfo) Scheduler.queue.remove(i);
                //System.err.println(gi.getID()+" PEs size = "+gi.PEs.size());
                r_cand.addGInfoInExec(gi);
                // set the resource ID for this gridletInfo (this is the final scheduling decision)
//                gi.setResourceID(r_cand.resource.getResourceID());
                // tell the JSS where to send which gridlet
  //              scheduler.submitJob(gi.getGridlet(), r_cand.resource.getResourceID());
                r_cand.is_ready = true;
                scheduled++;
                r_cand = null;
                i--;
                return scheduled;
            } else {
                return scheduled;
            }
        }

        return scheduled;
    }

    private static class Scheduler {

        private static boolean isSuitable(ResourceInfo ri, GridletInfo gi) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public Scheduler() {
        }

        private static class queue {

            private static void addLast(GridletInfo gi) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            private static int size() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            public queue() {
            }
        }

        private static class resourceInfoList {

            private static int size() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            public resourceInfoList() {
            }
        }
    }

    private static class GridletInfo {

        public GridletInfo() {
        }
    }

    private static class DeadlineComparator {

        public DeadlineComparator() {
        }
    }

    private static class ResourceInfo {

        private Object resource;
        private boolean is_ready;

        public ResourceInfo() {
        }

        private void addGInfoInExec(GridletInfo gi) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private boolean canExecuteNow(GridletInfo gi) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    private static class ri {

        public ri() {
        }
    }
}