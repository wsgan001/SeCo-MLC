/*
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program; if not, write to the Free Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

/*
 * Precision.java Copyright (C) 2003-2010 Knowledge Engineering Group http://www.ke.tu-darmstadt.de
 * 
 * Added by Johannes F�rnkranz
 */

package de.tu_darmstadt.ke.seco.algorithm.components.heuristics;

import de.tu_darmstadt.ke.seco.algorithm.components.ConfigurableProperty;
import de.tu_darmstadt.ke.seco.models.Rule;
import de.tu_darmstadt.ke.seco.stats.TwoClassConfusionMatrix;

/**
 * The seco package implements generic functionality for simple separate-and-conquer rule learning.
 * <pruningDepth>
 * This file provides evaluation for evaluating a rule with precision, i.e. tp/(tp+fp)
 *
 * @author Knowledge Engineering Group
 * @version $Revision: 354 $
 */
public class InvertedMEstimate extends ValueHeuristic {

    private static final long serialVersionUID = 2361978829616793056L;

    @ConfigurableProperty
    private double m = 22.466;

    /**
     * Constructor
     */
    public InvertedMEstimate() {
    }

    /**
     * evaluates a rule with precision
     *
     * @param r The candidate rule that should be evaluated.
     * @return the precision of the rule
     */
    @Override
    public double evaluateRule(final Rule r) {

        double P, N;
        double p = r.getStats().getNumberOfTruePositives();
        double n = r.getStats().getNumberOfFalsePositives();

        if (r.getPredecessor().length() > 0) {
            P = r.getPredecessor().getStats().getNumberOfTruePositives();
            N = r.getPredecessor().getStats().getNumberOfFalsePositives();
        } else {
            P = r.getStats().getNumberOfPositives();
            N = r.getStats().getNumberOfNegatives();
        }

        double div = ((P + N) - (p + n - m));
        if (div == 0) return 0;
        return (N - n + m * (P / (P + N))) / div;

    }

    @Override
    public double evaluateConfusionMatrix(final TwoClassConfusionMatrix confusionMatrix) {
        throw new UnsupportedOperationException();
    }

}
