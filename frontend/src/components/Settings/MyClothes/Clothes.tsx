import { Grid, GridItem } from '@chakra-ui/react';
import { IClothes } from '../../../interfaces';
import Cloth from './Cloth';

interface IClothesProps {
  clothes: IClothes[];
}

const Clothes = ({ clothes }: IClothesProps) => {
  return (
    <Grid gridTemplateColumns={['1fr', '1fr', '1fr 1fr 1fr']} gap="15px">
      {clothes.map((cloth) => {
        return (
          <GridItem key={cloth.id}>
            <Cloth isEditable={true} cloth={cloth} />
          </GridItem>
        );
      })}
    </Grid>
  );
};

export default Clothes;
