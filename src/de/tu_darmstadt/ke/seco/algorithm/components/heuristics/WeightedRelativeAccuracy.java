/*
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program; if not, write to the Free Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

/*
 * WRAcc.java Copyright (C) 2003-2010 Knowledge Engineering Group http://www.ke.tu-darmstadt.de
 * 
 * Added by Johannes F�rnkranz
 */

package de.tu_darmstadt.ke.seco.algorithm.components.heuristics;

import de.tu_darmstadt.ke.seco.models.Rule;
import de.tu_darmstadt.ke.seco.stats.TwoClassConfusionMatrix;

/**
 * (pruningDepth + n) / (P + N) * (pruningDepth / (pruningDepth + n) - P / (P + N))
 */
public class WeightedRelativeAccuracy extends ValueHeuristic {

    private static final long serialVersionUID = -6419114174931454765L;

    @Override
    public double evaluateRule(final Rule r) {
        return evaluateConfusionMatrix(r.getStats());
    }

    @Override
    public double evaluateConfusionMatrix(final TwoClassConfusionMatrix confusionMatrix) {
        double p = confusionMatrix.getNumberOfPredictedPositive();
        double n = confusionMatrix.getNumberOfPredictedNegative();
        double P = confusionMatrix.getNumberOfPositives();
        double N = confusionMatrix.getNumberOfNegatives();
        return (p + n) / (P + N) * (p / (p + n) - P / (P + N));
    }

}